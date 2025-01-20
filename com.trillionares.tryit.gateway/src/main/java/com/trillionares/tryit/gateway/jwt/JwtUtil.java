package com.trillionares.tryit.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

  @Value("${service.jwt.secret-key}")
  private String secretKey;

  // SecretKey 객체 생성
  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  // JWT 유효성 검증
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey()) // SecretKey 객체 사용
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("JWT token is malformed: {}", e.getMessage());
    } catch (SignatureException e) {
      log.error("JWT token signature is invalid: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT token is invalid: {}", e.getMessage());
    }
    return false;
  }

  // Authorization 헤더에서 토큰 추출
  public String extractToken(ServerHttpRequest request) {
    String authHeader = request.getHeaders().getFirst("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }
    return null;
  }

  // 토큰에서 사용자 이름 추출
  public String extractUsername(String token) {
    String username = extractClaims(token).getSubject();
    log.info("토큰에서 추출한 사용자 이름: {}", username);
    return username;
  }

  // 토큰에서 역할 추출
  public String extractRole(String token) {
    try {
      String role = extractClaims(token).get("role", String.class);
      log.info("토큰에서 추출한 역할: {}", role);
      return role;
    } catch (Exception e) {
      log.error("역할 추출 실패: {}", e.getMessage());
      return null;
    }
  }

  // 토큰에서 클레임 추출
  private Claims extractClaims(String token) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(getSigningKey()) // SecretKey 객체 사용
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      log.error("클레임 추출 실패: {}", e.getMessage());
      throw e; // 필요하다면 예외를 다시 던지거나 기본값 반환 처리
    }
  }


}

