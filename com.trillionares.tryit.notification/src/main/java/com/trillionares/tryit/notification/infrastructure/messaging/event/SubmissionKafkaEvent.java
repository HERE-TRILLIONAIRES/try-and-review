package com.trillionares.tryit.notification.infrastructure.messaging.event;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class SubmissionKafkaEvent {

  private UUID submissionId;
  private UUID userId;
  private UUID recruitmentId;
  private String status;

  @Setter
  private String messageId;

}
