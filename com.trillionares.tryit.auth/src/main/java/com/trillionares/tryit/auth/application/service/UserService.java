package com.trillionares.tryit.auth.application.service;

import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.domain.model.User;
import com.trillionares.tryit.auth.domain.repository.UserRepository;
import com.trillionares.tryit.auth.libs.exception.ErrorCode;
import com.trillionares.tryit.auth.libs.exception.GlobalException;
import com.trillionares.tryit.auth.presentation.dto.requestDto.PasswordUpdateReqDto;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignUpRequestDto;
import com.trillionares.tryit.auth.presentation.dto.responseDto.SignUpResponseDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SignUpResponseDto signup(SignUpRequestDto reqDto) {
    checkUsername(reqDto.getUsername());
    Role role = validateUserRole(reqDto.getRole());

    User savedUser = User.builder()
        .username(reqDto.getUsername())
        .fullname(reqDto.getFullname())
        .password(passwordEncoder.encode(reqDto.getPassword()))
        .email(reqDto.getEmail())
        .phoneNumber(reqDto.getPhoneNumber())
        .slackId(reqDto.getSlackId())
        .role(role)
        .isDeleted(false)
        .build();

    savedUser.setCreatedBy(reqDto.getUsername());

    savedUser = userRepository.save(savedUser);

    return new SignUpResponseDto(savedUser);
  }

  private void checkUsername(String username) {
    if (userRepository.existsByUsernameAndIsDeletedFalse(username)) {
      throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
    }
  }

  private Role validateUserRole(String role) {
    try {
      return Role.valueOf(role.toUpperCase()); // 입력된 문자열을 Enum 값으로 변환
    } catch (IllegalArgumentException e) {
      throw new GlobalException(ErrorCode.ROLE_NOT_FOUND);
    }
  }

}
