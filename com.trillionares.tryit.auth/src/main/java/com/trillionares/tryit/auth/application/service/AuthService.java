package com.trillionares.tryit.auth.application.service;


import com.trillionares.tryit.auth.libs.exception.CustomAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import com.trillionares.tryit.auth.infrastructure.config.CustomUserDetails;
import com.trillionares.tryit.auth.infrastructure.config.jwt.JwtUtil;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public String login(SignInRequestDto signInRequestDto) {
    try {
      // AuthenticationManager로 인증 처리
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              signInRequestDto.getUsername(),
              signInRequestDto.getPassword()
          )
      );

      // 인증 성공 시 사용자 정보와 역할 추출
      CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
      String username = customUserDetails.getUsername();
      String role = customUserDetails.getAuthorities().iterator().next().getAuthority();

      // JWT 생성
      return jwtUtil.createJwt(username, role);

    } catch (AuthenticationException e) {
      throw new CustomAuthenticationException("로그인 실패: " + e.getMessage());
    }
  }



}

