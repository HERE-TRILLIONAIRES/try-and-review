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
        .build();

    savedUser.setCreatedBy(reqDto.getUsername());

    savedUser = userRepository.save(savedUser);

    return new SignUpResponseDto(savedUser);
  }

  @Transactional
  public void updatePassword(@Valid PasswordUpdateReqDto reqDto, UUID userId) {
    User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
        .orElseThrow(() -> new GlobalException(ErrorCode.ID_NOT_FOUND));

    // 유저의 현재 비밀번호가 맞는지 확인
    if (!passwordEncoder.matches(reqDto.getCurrentPassword(), user.getPassword())) {
      throw new GlobalException(ErrorCode.INVALID_PASSWORD);
    }
    // 현재 비밀번호와 새 비밀번호가 같은지 확인
    if (passwordEncoder.matches(reqDto.getNewPassword(), user.getPassword())) {
      throw new IllegalArgumentException("새 비밀번호는 현재 비밀번호와 다르게 설정해야 합니다.");
    }
    user.updatePassword(passwordEncoder.encode(reqDto.getNewPassword()));
  }




  private void checkUsername(String username) {
    if (userRepository.existsByUsernameAndIsDeletedFalse(username)) {
      throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
    }
  }

  private void checkUserId(UUID userId) {
    if(!userRepository.existsByUserIdAndIsDeletedFalse(userId)) {
      throw new GlobalException(ErrorCode.ID_NOT_FOUND);
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
