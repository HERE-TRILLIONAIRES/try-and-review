package com.trillionares.tryit.notification.infrastructure.messaging.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubmissionSelectedEvent {

  private final String eventId;
  private final LocalDateTime timestamp;

  private final UUID submissionId;
  private final UUID userId;
  private final String submissionStatus; // TODO : Enum파일 자체 추가 필요할지 확인 필요

  public SubmissionSelectedEvent(String eventId, String eventType, LocalDateTime timestamp,
      UUID submissionId, UUID userId, String submissionStatus) {
    this.eventId = eventId;
    this.timestamp = timestamp;
    this.submissionId = submissionId;
    this.userId = userId;
    this.submissionStatus = submissionStatus;
  }
}
