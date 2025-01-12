package com.trillionares.tryit.notification.application.service;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import com.trillionares.tryit.notification.infrastructure.persistence.NotificationRepository;
import com.trillionares.tryit.notification.libs.client.auth.UserResponseDto;
import com.trillionares.tryit.notification.libs.client.auth.AuthServiceClient;
import com.trillionares.tryit.notification.libs.client.auth.RequestHeaderProvider;
import com.trillionares.tryit.notification.libs.exception.ErrorCode;
import com.trillionares.tryit.notification.libs.exception.ExceptionConverter;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import com.trillionares.tryit.notification.presentation.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

  private final ExceptionConverter exceptionConverter;
  private final NotificationRepository notificationRepository;
  private final SlackNotificationSender slackNotificationSender;
  private final AuthServiceClient authServiceClient;
  private final RequestHeaderProvider requestHeaderProvider;

  @Transactional
  public void createNotificationFromSubmissionEvent(SubmissionSelectedEvent event) {
    // 상태값 검증
    validateSubmissionStatus(event.getStatus());

    // 알림 엔티티 저장
    Notification notification = convertEventToNotification(event);
    Notification savedNotification = notificationRepository.save(notification);

    String slackId = getSlackId(savedNotification);

    // 슬랙 알림 발송
    slackNotificationSender.sendNotification(savedNotification, slackId);

    NotificationResponse.from(savedNotification);
  }

  private String getSlackId(Notification notification) {
    BaseResponse<UserResponseDto> response = authServiceClient.getUser(
        notification.getUserId(),
        requestHeaderProvider.getUsername(),
        requestHeaderProvider.getRole()
    );

    UserResponseDto userDto = response.getData();
    String slackId = userDto.getSlackId();

    if (slackId == null || slackId.isEmpty()) {
      throw new GlobalException(ErrorCode.SLACK_ID_NOT_FOUND);
    }

    return slackId;
  }

  private void validateSubmissionStatus(String status) {

    if (!"APPLIED".equals(status)) {
      throw new GlobalException(ErrorCode.INVALID_SUBMISSION_STATUS);
    }
  }

  private Notification convertEventToNotification(SubmissionSelectedEvent event) {

    return Notification.builder()
        .userId(event.getUserId())
        .submissionId(event.getSubmissionId())
        .build();
  }

  @Transactional(readOnly = true)
  public NotificationResponse getNotification(UUID notificationId) {

    Notification notification = notificationRepository.findById(notificationId)
        .orElseThrow(() -> new GlobalException(ErrorCode.NOTIFICATION_NOT_FOUND));

    return NotificationResponse.from(notification);
  }

  @Transactional(readOnly = true)
  public Page<NotificationResponse> getNotificationByStatus(NotificationStatus status,
      Pageable pageable) {

    return notificationRepository.findByNotificationStatus(status, pageable);
  }
}
