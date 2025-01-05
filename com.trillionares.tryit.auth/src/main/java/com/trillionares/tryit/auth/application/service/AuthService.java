package com.trillionares.tryit.auth.application.service;

import com.trillionares.tryit.auth.domain.repository.UserRepository;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignUpRequestDto;
import com.trillionares.tryit.auth.presentation.dto.responseDto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SignUpResponseDto createUser(SignUpRequestDto requestDto) {
    return null;
  }

}
