package com.trillionares.tryit.gateway.filter;

import com.trillionares.tryit.gateway.jwt.JwtUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GatewayFilter {

  private final JwtUtil jwtUtil;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String path = exchange.getRequest().getURI().getPath();

    // 인증 제외 경로를 직접 설정
    List<String> excludedPaths = List.of("/auth/signin", "/users/signup", "/users/internals/**");

    if (excludedPaths.stream().anyMatch(path::startsWith)) {
      log.info("인증 제외 경로 접근: {}", path);
      return chain.filter(exchange);
    }

    String token = jwtUtil.extractToken(exchange.getRequest());
    if (token == null || !jwtUtil.validateToken(token)) {
      log.error("유효하지 않은 JWT");
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }

    // JWT 유효성 검증 후 사용자 정보를 헤더에 추가
    String userName = jwtUtil.extractUsername(token);
    String userRole = jwtUtil.extractRole(token);
    return chain.filter(addHeaders(exchange, userName, userRole));
  }

  // 사용자 정보 헤더에 추가
  private ServerWebExchange addHeaders(ServerWebExchange exchange, String userName, String userRole) {
    return exchange.mutate()
        .request(exchange.getRequest().mutate()
            .header("X-Auth-Username", userName)
            .header("X-Auth-Role", userRole)
            .build())
        .build();
  }

}
