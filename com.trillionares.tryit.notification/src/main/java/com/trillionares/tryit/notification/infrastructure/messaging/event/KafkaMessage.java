package com.trillionares.tryit.notification.infrastructure.messaging.event;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class KafkaMessage {

  private UUID messageId;
  private String payload;
  private String eventTimestamp;
}
