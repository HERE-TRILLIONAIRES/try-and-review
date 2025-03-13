package com.trillionares.tryit.notification.infrastructure.messaging.event;

import com.trillionares.tryit.notification.domain.model.NotificationOutbox;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SubmissionKafkaEvent {

  private final UUID submissionId;
  private final UUID userId;
  private final UUID recruitmentId;
  private final String status;
  @Setter
  private String messageId;

  private SubmissionKafkaEvent(UUID submissionId, UUID userId, UUID recruitmentId,
      String status, String messageId) {
    this.submissionId = submissionId;
    this.userId = userId;
    this.recruitmentId = recruitmentId;
    this.status = status;
    this.messageId = messageId;
  }

  public static SubmissionKafkaEvent from(NotificationOutbox outbox) {
    return new SubmissionKafkaEvent(
        outbox.getSubmissionId(),
        outbox.getUserId(),
        outbox.getRecruitmentId(),
        outbox.getStatus(),
        outbox.getMessageId()
    );
  }
}
