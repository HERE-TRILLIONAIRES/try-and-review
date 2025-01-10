package com.trillionares.tryit.notification.application.dto.slack;

import com.trillionares.tryit.notification.domain.model.Notification;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackMessage {
  private String text;
  private String submissionId;
  private String userId;

  public static SlackMessage from(Notification notification) {
    return SlackMessage.builder()
        .text("체험단으로 선정되셨습니다!")
        .submissionId(notification.getSubmissionId().toString())
        .userId(notification.getUserId().toString())
        .build();
  }
}
