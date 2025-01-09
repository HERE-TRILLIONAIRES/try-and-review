package com.trillionares.tryit.auth.infrastructure.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final CustomUserDetailsService customUserDetailsService; // 사용자 정보를 로드하는 서비스
  private final PasswordEncoder passwordEncoder; // 비밀번호를 검증하기 위한 인코더

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName(); // 입력된 username
    String password = authentication.getCredentials().toString(); // 입력된 password

    // 사용자 정보 로드
    CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

    // 비밀번호 검증
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid username or password");
    }

    // 인증 성공 시 UsernamePasswordAuthenticationToken 반환
    return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
