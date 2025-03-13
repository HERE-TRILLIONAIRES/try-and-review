package com.trillionares.tryit.notification.infrastructure.messaging.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillionares.tryit.notification.application.service.NotificationEventService;
import com.trillionares.tryit.notification.libs.exception.ErrorCode;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionEventConsumer {

  private final NotificationEventService notificationEventService; // 추가
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = "tryit-completed",
      groupId = "${spring.kafka.consumer.group-id}"
  )

  public void consumeSubmissionEvent(String message) {
    try {
      KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);

      log.info("첫번째 역직렬화 KafkaMessage: {}", kafkaMessage);

      SubmissionKafkaEvent event = objectMapper.readValue( // payload 역직렬화
          kafkaMessage.getPayload(),
          SubmissionKafkaEvent.class
      );
      event.setMessageId(kafkaMessage.getMessageId());
      log.info("두번째 역직렬화 SubmissionKafkaEvent: {}", event);

      notificationEventService.processSubmissionNotification(kafkaMessage, event);

    } catch (JsonProcessingException e) {
      log.error("Failed to process kafka message: {}", message, e);

      throw new GlobalException(ErrorCode.EXTERNAL_SERVICE_ERROR, e.getMessage());
    }
  }
}
