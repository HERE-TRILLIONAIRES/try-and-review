package com.trillionares.tryit.gateway.filter;

import com.trillionares.tryit.gateway.jwt.JwtUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GatewayFilter {

  private final JwtUtil jwtUtil;
  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    log.info("JwtAuthFilter 실행됨");  // 기본 로그

    String path = exchange.getRequest().getURI().getPath();

    // 인증 제외 경로 설정
    List<String> excludedPaths = List.of("/auth/signin", "/users/signup", "/users/internals/**", "/actuator/health");

    // 로그 추가: 요청 경로와 인증 제외 경로 매칭 여부 확인
    log.info("요청 경로: {}", path);
    log.info("인증 제외 경로에 포함 여부: {}", excludedPaths.stream().anyMatch(pattern -> pathMatcher.match(pattern, path)));

    // 인증 제외 경로일 경우 필터 체인을 그대로 통과
    if (excludedPaths.stream().anyMatch(pattern -> pathMatcher.match(pattern, path))) {
      log.info("인증 제외 경로 접근: {}", path);
      return chain.filter(exchange);
    }

    String token = jwtUtil.extractToken(exchange.getRequest());
    if (token == null || !jwtUtil.validateToken(token)) {
      log.error("유효하지 않은 JWT");
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }

    // JWT에서 사용자 정보 추출
    String userName = jwtUtil.extractUsername(token);
    String userRole = jwtUtil.extractRole(token);

    // 헤더 추가 및 최종 로그 확인
    return chain.filter(addHeaders(exchange, userName, userRole));
  }

  private ServerWebExchange addHeaders(ServerWebExchange exchange, String userName, String userRole) {
    log.info("헤더 추가 시도 - Username: {}, Role: {}", userName, userRole);

    ServerWebExchange mutatedExchange = exchange.mutate()
        .request(exchange.getRequest().mutate()
            .header("X-Auth-Username", userName != null ? userName : "없음")
            .header("X-Auth-Role", userRole != null ? userRole : "없음")
            .build())
        .build();

    // 최종 요청 헤더 로그
    log.info("최종 요청 헤더: {}", mutatedExchange.getRequest().getHeaders());
    return mutatedExchange;
  }


}
