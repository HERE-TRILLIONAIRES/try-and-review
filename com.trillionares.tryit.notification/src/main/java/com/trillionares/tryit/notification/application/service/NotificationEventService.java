package com.trillionares.tryit.notification.application.service;

import com.trillionares.tryit.notification.domain.model.NotificationOutbox;
import com.trillionares.tryit.notification.domain.repository.NotificationOutboxRepository;
import com.trillionares.tryit.notification.infrastructure.messaging.event.KafkaMessage;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionKafkaEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationEventService {

  private final NotificationOutboxRepository outboxRepository;
  private final NotificationService notificationService;

  @Transactional
  public void processSubmissionNotification(KafkaMessage message, SubmissionKafkaEvent event) {
    log.info("이벤트 처리 시작 - messageId: {}", message.getMessageId());

    // outbox에 이벤트 저장
    NotificationOutbox outbox = NotificationOutbox.builder()
        .message(message)
        .event(event)
        .build();
    outboxRepository.save(outbox);

    try {
      boolean success = notificationService.createNotificationFromSubmissionEvent(event);

      if (success) {
        outbox.markAsProcessed();
        log.info("이벤트 처리 성공 - messageId: {}", message.getMessageId());
      } else {
        outbox.markAsFailed();
        log.warn("이벤트 처리 실패 - messageId: {}", message.getMessageId());
      }
      outboxRepository.save(outbox);

    } catch (Exception e) {
      outbox.markAsFailed(); // 재시도 카운트 증가 및 상태 업데이트
      outboxRepository.save(outbox); // 변경사항 저장
      log.error("이벤트 처리 예외 - messageID: {}, ERROR: {}", message.getMessageId(), e.getMessage());
    }
  }
}
