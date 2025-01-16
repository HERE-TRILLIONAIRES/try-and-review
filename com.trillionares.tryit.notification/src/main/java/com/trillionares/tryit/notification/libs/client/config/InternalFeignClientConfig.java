package com.trillionares.tryit.notification.libs.client.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 내부 통신용
 */

public class InternalFeignClientConfig {

  @Bean
  public RequestInterceptor internalRequestInterceptor() {
    return requestTemplate -> {
      requestTemplate.headers().clear(); // 기존 헤더 제거
      requestTemplate.header("X-Internal-Call", "true");
    };
  }
}
