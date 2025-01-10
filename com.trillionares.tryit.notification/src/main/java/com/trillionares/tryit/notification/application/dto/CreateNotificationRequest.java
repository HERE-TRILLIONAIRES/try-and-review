package com.trillionares.tryit.notification.application.dto;

import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import java.util.UUID;

public class CreateNotificationRequest {

  private UUID userId;
  private UUID submissionId;
  private NotificationStatus notificationStatus;
  private Integer attemptCount;
  // createdAt
  // createdBy

  public Notification to() {
    return Notification.builder()
        .userId(this.userId)
        .submissionId(this.submissionId)
        .notificationStatus(this.notificationStatus)
        .attemptCount(this.attemptCount)
        // .createdAt
        // .createdBy
        .build();
  }
}
