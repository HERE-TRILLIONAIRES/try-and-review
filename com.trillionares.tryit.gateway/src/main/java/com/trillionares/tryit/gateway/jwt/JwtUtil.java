package com.trillionares.tryit.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

  @Value("${service.jwt.secret-key}")
  private String secretKey;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
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

