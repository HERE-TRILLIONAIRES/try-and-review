package com.trillionares.tryit.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillionares.tryit.notification.application.dto.slack.SlackNotificationSender;
import com.trillionares.tryit.notification.application.service.NotificationService;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionKafkaEvent;
import com.trillionares.tryit.notification.infrastructure.persistence.NotificationRepository;
import com.trillionares.tryit.notification.libs.client.auth.AuthClient;
import com.trillionares.tryit.notification.libs.client.auth.FeignUserIdResponseDto;
import com.trillionares.tryit.notification.presentation.dto.BaseResponse;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

  @Mock
  private NotificationRepository notificationRepository;

  @InjectMocks
  private NotificationService notificationService;

  @Mock
  private SlackNotificationSender slackNotificationSender;

  @Mock
  private AuthClient authClient;

  @Test
  void 알림_발송_성공_테스트() throws JsonProcessingException {
    // given
    String messageId = "test-message-id";
    String slackId = "@1111111111";

    String payload = """
        {
            "submissionId": "eacad322-798e-4744-a4a4-69ebbdb9e0e4",
            "userId": "0823f7ce-ba76-404c-8373-d2b6f4348d94",
            "recruitmentId": "a96e1693-9e9a-4077-beb9-a3ea6f7f256c",
            "status": "SELECTED"
        }
        """;

    // 카프카 역직렬화
    ObjectMapper objectMapper = new ObjectMapper();
    SubmissionKafkaEvent event = objectMapper.readValue(payload, SubmissionKafkaEvent.class);
    event.setMessageId(messageId);

    UUID userId = UUID.fromString("0823f7ce-ba76-404c-8373-d2b6f4348d94");
    UUID submissionId = UUID.fromString("eacad322-798e-4744-a4a4-69ebbdb9e0e4");

    Notification mockNotification = Notification.builder()
        .messageId(messageId)
        .submissionId(UUID.fromString("a2411206-94f7-46e3-942c-83b1cd6179b7"))
        .userId(UUID.fromString("0075ac5e-b212-4419-891b-9e4a0b4190cb"))
        .build();

    // feign user 관련 모킹
    FeignUserIdResponseDto userDto = mock(FeignUserIdResponseDto.class); // 가짜 객체 생성
    when(userDto.getSlackId()).thenReturn(slackId);

    BaseResponse<FeignUserIdResponseDto> authResponse = mock(BaseResponse.class);
    when(authResponse.getData()).thenReturn(userDto);

    when(authClient.getUserId(any(UUID.class)))
        .thenReturn(authResponse);

    // repository 모킹
    when(notificationRepository.save(any(Notification.class)))
        .thenReturn(mockNotification);

    when(notificationRepository.findByMessageId(messageId))
        .thenReturn(Optional.empty());

    // ArgumentCaptor 준비
    ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);

    // when
    notificationService.createNotificationFromSubmissionEvent(event);

    // then
    // 저장된 Notification 객체 캡처 및 검증
    verify(notificationRepository).save(notificationCaptor.capture());
    Notification savedNotification = notificationCaptor.getValue();

    assertThat(savedNotification.getMessageId()).isEqualTo(messageId);
    assertThat(savedNotification.getUserId()).isEqualTo(userId);
    assertThat(savedNotification.getSubmissionId()).isEqualTo(submissionId);
  }
}
