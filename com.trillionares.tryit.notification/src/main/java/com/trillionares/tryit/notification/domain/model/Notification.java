package com.trillionares.tryit.notification.domain.model;

import com.trillionares.tryit.notification.domain.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "p_notification", schema = "notification")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "notification_id", updatable = false)
  private UUID notificationId;

  @Column(name = "user_id", columnDefinition = "uuid")
  private UUID userId;

  @Column(name = "submission_id", columnDefinition = "uuid")
  private UUID submissionId;

  @Enumerated(EnumType.STRING)
  @Column(name = "notification_status")
  private NotificationStatus notificationStatus = NotificationStatus.PENDING; // 초기상태 설정

  @Column(name = "attempt_count")
  private Integer attemptCount = 0;

  public void increaseAttemptCount() {
    this.attemptCount++;
    updateStatusBasedOnAttempt();
  }

  public void updateStatusBasedOnAttempt() {
    if (this.attemptCount >= 3) { // 0, 1, 2 최대 3회
      this.notificationStatus = NotificationStatus.FAILED;
    }
  }

  public void markAsDelivered() {
    this.notificationStatus = NotificationStatus.SENT;
  }

  @Builder
  public Notification(UUID notificationId, UUID userId, UUID submissionId, Integer attemptCount
  ) {
    this.notificationId = notificationId;
    this.userId = userId;
    this.submissionId = submissionId;
    this.attemptCount = attemptCount;
  }
}
