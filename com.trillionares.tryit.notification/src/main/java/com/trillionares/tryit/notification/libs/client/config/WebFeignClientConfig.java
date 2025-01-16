package com.trillionares.tryit.notification.libs.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
@ConditionalOnWebApplication
public class WebFeignClientConfig {

  private final RequestHeaderProvider requestHeaderProvider;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      String username = requestHeaderProvider.getUsername();
      String role = requestHeaderProvider.getRole();

      requestTemplate.header("X-Auth-Username", username);
      requestTemplate.header("X-Auth-Role", role);
    };
  }
}
