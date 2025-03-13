package com.trillionares.tryit.notification.application.dto.slack;

import com.trillionares.tryit.notification.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackNotificationSender {

  @Value("${slack.webhook.url}")
  private String webhookUrl;

  private final RestTemplate restTemplate;

  public boolean sendNotification(Notification notification, String slackId, String status) {
    log.info("Slack API 호출 시작 - messageId: {}", notification.getMessageId());

    SlackMessage message = SlackMessage.from(notification, slackId, status); // 슬랙 메세지 생성

    try {
      restTemplate.postForEntity(webhookUrl, message, String.class);
      log.info("Slack API 호출 성공 - messageId: {}", notification.getMessageId());
      return true;

    } catch (Exception e) {
      log.error("Slack API 호출 실패 - messageId: {}, error: {}",
          notification.getMessageId(), e.getMessage());
      return false;
    }
  }
}
