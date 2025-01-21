package com.trillionares.tryit.notification.application.service;

import com.trillionares.tryit.notification.application.dto.response.NotificationResponse;
import com.trillionares.tryit.notification.application.dto.slack.SlackNotificationSender;
import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionKafkaEvent;
import com.trillionares.tryit.notification.infrastructure.persistence.NotificationRepository;
import com.trillionares.tryit.notification.libs.client.auth.AuthClient;
import com.trillionares.tryit.notification.libs.client.auth.FeignUserIdResponseDto;
import com.trillionares.tryit.notification.libs.client.auth.FeignUsernameResponseDto;
import com.trillionares.tryit.notification.libs.exception.ErrorCode;
import com.trillionares.tryit.notification.libs.exception.ExceptionConverter;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import com.trillionares.tryit.notification.presentation.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

  private final ExceptionConverter exceptionConverter;
  private final NotificationRepository notificationRepository;
  private final SlackNotificationSender slackNotificationSender;
  private final AuthClient authClient;
  private final NotificationRoleValidation roleValidation;

  /**
   * Kafka 이벤트를 기반으로 알림을 생성하고 Slack으로 발송합니다.
   * @param event 체험단 신청(submission) 카프카 이벤트를 통해 받아온 값
   */
  @Transactional
  public void createNotificationFromSubmissionEvent(SubmissionKafkaEvent event) {

    validateNotification(event);
    Notification notification = notificationRepository.save(
        convertEventToNotification(event));

    sendNotificationToSlack(notification, event.getStatus());
  }

  /**
   * 알림 유효성을 검사합니다 (동일한 messageId로 발송된 알림이 있는지 확인, 실패 경우 재시도 횟수 검증)
   * @param event 체험단 신청(submission) 카프카 이벤트를 통해 받아온 값
   */
  private void validateNotification(SubmissionKafkaEvent event) {
    notificationRepository.findByMessageId(event.getMessageId())
        .ifPresent(notification -> {
          if (notification.getNotificationStatus() == NotificationStatus.SENT) { //
            throw new GlobalException(ErrorCode.DUPLICATE_MESSAGE_ID);
          }
          validateRetryCount(notification);
        });
  }

  /**
   * 알림 재시도 횟수가 3회를 초과 했는지 확인합니다.
   * @param notification 알림 객체
   */
  private void validateRetryCount(Notification notification) {
    if (notification.getAttemptCount() >= 3) {
      log.warn("재시도 횟수 3회 초과 {}", notification.getMessageId());
      throw new GlobalException(ErrorCode.MAX_RETRY_EXCEEDED);
    }
  }

  /**
   * 생성된 알림을 Slack으로 발송합니다.
   * @param notification 알림 객체
   * @param status 알림 상태 정보
   */
  private void sendNotificationToSlack(Notification notification, String status) {
    try {
      String slackId = getSlackId(notification);
      slackNotificationSender.sendNotification(notification, slackId, status);
      notification.markAsDelivered();

    } catch (Exception e) {
      notification.increaseAttemptCount();
      log.error("알림 발송 실패, attempt {}: {}",
          notification.getAttemptCount(), e.getMessage());
      throw e;
    }
  }

  /**
   * Slack ID를 구하는 메서드
   * @param notification 알림 객체
   * @return Auth Service에서 조회한 Slack ID
   */
  private String getSlackId(Notification notification) {

    BaseResponse<FeignUserIdResponseDto> response = authClient.getUserId(
        notification.getUserId());

    FeignUserIdResponseDto userDto = response.getData();
    String slackId = userDto.getSlackId();
    log.info("slackId {}", slackId);

    if (slackId == null || slackId.isEmpty()) { // TODO: Feign error 추가 필요
      throw new GlobalException(ErrorCode.SLACK_ID_NOT_FOUND);
    }
    return slackId;
  }

  /**
   * Kafka 이벤트 데이터를 알림 엔티티로 변환하는 메서드 입니다.
   * @param event Kafka 이벤트 데이터
   * @return 생성된 알림 데이터
   */
  private Notification convertEventToNotification(SubmissionKafkaEvent event) {

    return Notification.builder()
        .userId(event.getUserId())
        .submissionId(event.getSubmissionId())
        .messageId(event.getMessageId())
        .build();
  }

  @Transactional(readOnly = true)
  public NotificationResponse getNotification(UUID notificationId, String role, String username) {

    Notification notification = notificationRepository.findById(notificationId)
        .orElseThrow(() -> new GlobalException(ErrorCode.NOTIFICATION_NOT_FOUND));

    BaseResponse<FeignUsernameResponseDto> response = authClient.getUserByUsername(username);
    UUID currentUserId = response.getData().getUserId();

    if (!roleValidation.isAdmin(role) &&
        !roleValidation.isNotificationOwner(notification.getUserId(), currentUserId)) {
      throw new GlobalException(ErrorCode.UNAUTHORIZED);
    }

    return NotificationResponse.from(notification);
  }

  @Transactional(readOnly = true)
  public Page<NotificationResponse> getNotificationByStatus(
      NotificationStatus status,
      String role,
      String username,
      Pageable pageable
  ) {

    BaseResponse<FeignUsernameResponseDto> response = authClient.getUserByUsername(username);
    UUID currentUserId = response.getData().getUserId();

    return roleValidation.isAdmin(role)
        ? notificationRepository.findByNotificationStatus(status, pageable)
        : notificationRepository.findByNotificationStatusAndUserId(status, currentUserId, pageable);
  }
}
