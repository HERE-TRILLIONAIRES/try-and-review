package com.trillionares.tryit.notification.infrastructure.scheduler;

import com.trillionares.tryit.notification.application.service.NotificationService;
import com.trillionares.tryit.notification.domain.model.NotificationOutbox;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.domain.repository.NotificationOutboxRepository;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionKafkaEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationOutboxScheduler {

  private final NotificationOutboxRepository notificationOutboxRepository;
  private final NotificationService notificationService;

  @Scheduled(fixedDelayString = "${notification.retry.delay:10000}")
  public void processFailedNotifications() {
    log.info("알림 재시도 스케줄러 실행");

    List<NotificationOutbox> pendingOutboxes = notificationOutboxRepository
        .findByOutboxStatus(NotificationStatus.PENDING);

    if (!pendingOutboxes.isEmpty()) {
      log.info("알림 재처리 시작 - 대상 수: {}", pendingOutboxes.size());

      for (NotificationOutbox outbox : pendingOutboxes) {

        if (!outbox.canRetry()) {
          continue;
        }

        try {
          // 알림 처리
          boolean success =
              notificationService.sendNotificationToSlack(outbox.getEventId(), outbox.getStatus());

          if (success) { // 성공 처리
            outbox.markAsProcessed();
            log.info("알림 재처리 성공 - messageId: {}", outbox.getMessageId());
          } else {
            outbox.markAsFailed();
            log.warn("알림 재처리 실패 - messageId: {}, 시도 횟수: {}/3",
                outbox.getMessageId(), outbox.getRetryCount());
          }
        } catch (Exception e) {
          outbox.markAsFailed();
          log.error("알림 재처리 중 예외 발생 - messageId: {}, 시도 횟수: {}/3, 오류: {}",
              outbox.getMessageId(), outbox.getRetryCount(), e.getMessage());
        }
        notificationOutboxRepository.save(outbox);
      }
    }
  }
}
