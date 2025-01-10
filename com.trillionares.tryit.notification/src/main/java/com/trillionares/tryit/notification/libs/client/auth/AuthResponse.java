package com.trillionares.tryit.notification.libs.client.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

  private String slackId;
}
