package com.trillionares.tryit.notification.application.service;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.repository.NotificationRepository;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

  // private final ExceptionConverter exceptionConverter;
  private final NotificationRepository notificationRepository;

  @Transactional
  public NotificationResponse createNotificationFromSubmissionEvent(SubmissionSelectedEvent event) {
    // 상태값 검증
    validateSubmissionStatus(event.getSubmissionStatus());

    // 알림 엔티티 저장
    Notification notification = createNotificationEvent(event);
    Notification savedNotification = notificationRepository.save(notification);

    return NotificationResponse.from(savedNotification);
  }

  private void validateSubmissionStatus(String status) {
    if (!"SELECTED".equals(status)) {
      // throw new GlobalException(ErrorCode.INVALID_SUBMISSION_STATUS);
    }
  }

  private Notification createNotificationEvent(SubmissionSelectedEvent event) {
    return Notification.builder()
        .userId(event.getUserId())
        .submissionId(event.getSubmissionId())
        .build();
  }
}
