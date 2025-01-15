package com.trillionares.tryit.notification.application.dto.slack;

import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.libs.exception.ErrorCode;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackMessage {
  private String channel;
  private String text;

  public static SlackMessage from(Notification notification, String slackId, String status) {
    String message = createMessageByStatus(status);

    return SlackMessage.builder()
        .channel(slackId)
        .text(String.format("<@%s> %s\n제출 ID: %s",
            slackId,
            message,
            notification.getSubmissionId()))
        .build();
  }

  private static String createMessageByStatus(String status) {
    return switch (status) {
      case "APPLIED" -> "체험단 신청이 완료되었습니다.";
      case "SELECTED" -> "체험단에 선정되었습니다";
      case "CANCELLED" -> "체험단 신청이 취소되었습니다";
      case "FAILED" -> "체험단에 선정되지 않았습니다";
      default -> throw new GlobalException(ErrorCode.INVALID_SUBMISSION_STATUS);
    };
  }
}
