package com.trillionares.tryit.gateway.config;

import com.trillionares.tryit.gateway.filter.JwtAuthFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  private final JwtAuthFilter jwtAuthFilter;

  public GatewayConfig(JwtAuthFilter jwtAuthFilter) {
    this.jwtAuthFilter = jwtAuthFilter;
  }

  @Bean
  public GlobalFilter customGlobalFilter() {
    return (exchange, chain) -> jwtAuthFilter.filter(exchange, chain);
  }

}
