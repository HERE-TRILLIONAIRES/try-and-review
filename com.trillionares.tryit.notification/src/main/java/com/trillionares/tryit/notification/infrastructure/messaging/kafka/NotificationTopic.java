package com.trillionares.tryit.notification.infrastructure.messaging.kafka;

public enum NotificationTopic {

  TRYIT_COMPLETED("tryit-completed"); // 알림 생성


  private final String topic;

  NotificationTopic(String topic) {
    this.topic = topic;
  }

  public String getTopic() {
    return topic;
  }
}
