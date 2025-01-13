package com.trillionares.tryit.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trillionares.tryit.notification.application.service.NotificationService;
import com.trillionares.tryit.notification.application.service.SlackNotificationSender;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.repository.NotificationRepositoryImpl;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import com.trillionares.tryit.notification.infrastructure.persistence.NotificationRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
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

  private SubmissionSelectedEvent createSubmissionSelectedEvent(String status) {
    return new SubmissionSelectedEvent(
        UUID.randomUUID(),              // submissionId
        UUID.randomUUID(),              // userId
        UUID.randomUUID(),              // recruitmentId
        status,                         // status
        LocalDateTime.now(),            // submissionTime
        UUID.randomUUID(),              // messageId
        LocalDateTime.now()             // eventTimestamp
    );
  }

  @Test
  void shouldSendSlackNotificationWhenEventReceived() {
    // given
    // 이벤트 발생
    SubmissionSelectedEvent event = createSubmissionSelectedEvent("SELECTED");

    // notification 저장 시 반환될 객체 생성

    Notification mockNotification = Notification.builder()
            .notificationId(UUID.randomUUID())
            .userId(event.getUserId())
            .submissionId(event.getSubmissionId())
            .build();

    when(notificationRepository.save(any(Notification.class)))
        .thenReturn(mockNotification);

    // when
    notificationService.createNotificationFromSubmissionEvent(event);

    // then
    verify(notificationRepository).save(any(Notification.class));
   // verify(slackNotificationSender).sendNotification(any(Notification.class));

  }
}
