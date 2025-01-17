package com.trillionares.tryit.notification.libs.client.auth;

import java.util.UUID;
import lombok.Getter;

@Getter
public class FeignUsernameResponseDto {

  private UUID userId;
  private String username;
  private String role;
  private String slackId;
}
