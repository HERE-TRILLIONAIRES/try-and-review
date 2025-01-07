package com.trillionares.tryit.notification.application.service;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.repository.NotificationRepository;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import com.trillionares.tryit.notification.libs.exception.ErrorCode;
import com.trillionares.tryit.notification.libs.exception.ExceptionConverter;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

  private final ExceptionConverter exceptionConverter;
  private final NotificationRepository notificationRepository;
  private final SlackNotificationSender slackNotificationSender;

  @Transactional
  public void createNotificationFromSubmissionEvent(SubmissionSelectedEvent event) {
    // 상태값 검증
    validateSubmissionStatus(event.getSubmissionStatus());

    // 알림 엔티티 저장
    Notification notification = createNotificationEvent(event);
    Notification savedNotification = notificationRepository.save(notification);

    // 슬랙 알림 발송
    slackNotificationSender.sendNotification(savedNotification);

    NotificationResponse.from(savedNotification);
  }

  private void validateSubmissionStatus(String status) {
    if (!"SELECTED".equals(status)) {
       throw new GlobalException(ErrorCode.INVALID_SUBMISSION_STATUS);
    }
  }

  private Notification createNotificationEvent(SubmissionSelectedEvent event) {
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
}
