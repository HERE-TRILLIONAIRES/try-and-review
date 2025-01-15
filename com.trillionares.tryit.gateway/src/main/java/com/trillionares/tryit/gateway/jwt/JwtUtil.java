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

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(secretKey)
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

  // 게이트웨이로 요청이 들어오면 토큰을 추출
  public String extractToken(ServerHttpRequest request) {
    String authHeader = request.getHeaders().getFirst("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }
    return null;
  }

  // 토큰에서 사용자 이름 추출
  public String extractUsername(String token) {
    return extractClaims(token).get("username", String.class);
  }

  // 토큰에서 역할 추출
  public String extractRole(String token) {
    return extractClaims(token).get("role", String.class);
  }

  // 토큰에서 클레임 부분 추출
  private Claims extractClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

}

