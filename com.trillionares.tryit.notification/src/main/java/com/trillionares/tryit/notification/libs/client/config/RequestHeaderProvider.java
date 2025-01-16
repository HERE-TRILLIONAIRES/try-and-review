package com.trillionares.tryit.notification.libs.client.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnWebApplication
public class RequestHeaderProvider {

  private final HttpServletRequest request;

  public String getUsername() {
    return request.getHeader("X-Auth-Username");
  }

  public String getRole() {
    return request.getHeader("X-Auth-Role");
  }
}
