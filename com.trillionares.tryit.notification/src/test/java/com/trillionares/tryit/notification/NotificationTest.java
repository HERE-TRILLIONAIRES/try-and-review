package com.trillionares.tryit.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillionares.tryit.notification.domain.repository.NotificationRepositoryImpl;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class NotificationTest {

  @Autowired
  private KafkaTemplate<String, SubmissionSelectedEvent> kafkaTemplate;

  @Autowired
  private NotificationRepositoryImpl notificationQueryRepositoryImpl;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${kafka.topic.submission-selected}")
  private String topic;
}

