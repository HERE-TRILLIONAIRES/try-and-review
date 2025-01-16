package com.trillionares.tryit.notification.infrastructure.messaging.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillionares.tryit.notification.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionEventConsumer {

  private final NotificationService notificationService;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = "tryit-completed",
      groupId = "${spring.kafka.consumer.group-id}"
  )

  public void handleSubmissionEvent(String message) throws JsonProcessingException {
    KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);

    log.info("카프카 통신 KafkaMessage: {}", kafkaMessage);

        SubmissionKafkaEvent event = objectMapper.readValue(
            kafkaMessage.getPayload(),
            SubmissionKafkaEvent.class
        );
    log.info("두번째 역직렬화 SubmissionKafkaEvent: {}", event);

    notificationService.createNotificationFromSubmissionEvent(event);
  }
}
