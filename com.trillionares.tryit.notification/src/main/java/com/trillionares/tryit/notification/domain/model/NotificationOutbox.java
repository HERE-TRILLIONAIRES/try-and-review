package com.trillionares.tryit.notification.domain.model;

import com.trillionares.tryit.notification.domain.common.base.BaseEntity;
import com.trillionares.tryit.notification.infrastructure.messaging.event.KafkaMessage;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionKafkaEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationOutbox extends BaseEntity {

  @Id
  @Column(name = "event_id", updatable = false)
  private UUID eventId;

  @Column(name = "message_id", nullable = false)
  private String messageId;

  @Column(nullable = false)
  private UUID submissionId;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private UUID recruitmentId;

  @Column(nullable = false)
  private String status;

  private int retryCount;

  @Enumerated(EnumType.STRING)
  private NotificationStatus outboxStatus;

  private LocalDateTime processedAt;

  public void markAsProcessed() {
    this.outboxStatus = NotificationStatus.SENT;
    this.processedAt = LocalDateTime.now();
  }

  public void markAsFailed() {
    this.retryCount++;
    if (this.retryCount >= 3) {
      this.outboxStatus = NotificationStatus.FAILED;
    } else {
      this.outboxStatus = NotificationStatus.PENDING;
    }
  }

  public boolean canRetry() {
    return this.retryCount < 3 && this.outboxStatus != NotificationStatus.SENT;
  }

  @Builder
  public NotificationOutbox(KafkaMessage message, SubmissionKafkaEvent event) {
    this.eventId = UUID.fromString(message.getMessageId());
    this.messageId = message.getMessageId();
    this.submissionId = event.getSubmissionId();
    this.userId = event.getUserId();
    this.recruitmentId = event.getRecruitmentId();
    this.status = event.getStatus();
    this.outboxStatus = NotificationStatus.PENDING;
    this.retryCount = 0;
  }
}
