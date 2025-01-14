package com.trillionares.tryit.notification.libs.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

  private final RequestHeaderProvider requestHeaderProvider;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      String username = requestHeaderProvider.getUsername();
      String role = requestHeaderProvider.getRole();

    };
  }
}
