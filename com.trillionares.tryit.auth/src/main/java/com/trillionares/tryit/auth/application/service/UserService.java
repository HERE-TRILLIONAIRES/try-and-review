package com.trillionares.tryit.auth.application.service;

import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.auth.application.dto.InfoByUsernameResponseDto;
import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.domain.model.User;
import com.trillionares.tryit.auth.domain.repository.UserRepository;
import com.trillionares.tryit.auth.libs.exception.ErrorCode;
import com.trillionares.tryit.auth.libs.exception.GlobalException;
import com.trillionares.tryit.auth.presentation.dto.requestDto.PasswordUpdateReqDto;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignUpRequestDto;
import com.trillionares.tryit.auth.presentation.dto.requestDto.UserInfoUpdateReqDto;
import com.trillionares.tryit.auth.presentation.dto.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserResponseDto signup(SignUpRequestDto reqDto) {
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

    return new UserResponseDto(savedUser);
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

  @CacheEvict(cacheNames = "userCache", key = "#userId")
  @Transactional
  public UserResponseDto updateUserInfo(@Valid UUID userId, UserInfoUpdateReqDto reqDto) {
    User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
        .orElseThrow(() -> new GlobalException(ErrorCode.ID_NOT_FOUND));

    user.updateUserInfo(reqDto.getFullname(), reqDto.getEmail(), reqDto.getPhoneNumber(), reqDto.getSlackId());
    log.info("캐시 삭제됨: {}", userId); // 캐싱 업데이트 여부 확인용
    return new UserResponseDto(user);
  }

  @Transactional
  public void deleteUser(UUID userId) {
    User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

    if (user.isDeleted()) {
      throw new GlobalException(ErrorCode.USER_ALREADY_DELETED);
    }

    user.deleteUser(); // 소프트 삭제 처리
    userRepository.save(user); // 변경 사항 저장
  }

  @Cacheable(cacheNames = "userCache", key = "#username")
  @Transactional(readOnly = true)
  public InfoByUsernameResponseDto getUserByUsername(String username) {
    log.info("캐싱 적용 전, DB에서 사용자 정보 조회: {}", username); // 캐싱 여부 확인용
    User user = userRepository.findByUsernameAndIsDeletedFalse(username)
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

    return new InfoByUsernameResponseDto(user);
  }

  @Cacheable(cacheNames = "userCache", key = "#userId")
  @Transactional(readOnly = true)
  public UserResponseDto getUser(UUID userId) {
    log.info("캐싱 적용 전, DB에서 사용자 정보 조회: {}", userId); // 캐싱 여부 확인용
    User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

    return new UserResponseDto(user);
  }

  @Cacheable(cacheNames = "userCache", key = "#userId")
  @Transactional(readOnly = true)
  public UserResponseDto getInternalUser(UUID userId) {
    log.info("캐싱 적용 전, DB에서 내부용 사용자 정보 조회: {}", userId); // 캐싱 여부 확인용
    User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

    return new UserResponseDto(user);
  }

  // 사용자 목록 조회 queryDsl+paging
  @Transactional(readOnly = true)
  @PreAuthorize("hasAuthority('ADMIN')")
  public PagedModel<UserResponseDto> getUsers(List<UUID> uuidList, String username, String email, Role role,
      LocalDateTime startDateTime, LocalDateTime endDateTime, Predicate predicate,
      Pageable pageable) {

    Page<UserResponseDto> userResponseDtoPage
        = userRepository.findAllByConditions(uuidList, username, email, role, startDateTime, endDateTime, predicate, pageable);

    return new PagedModel<>(userResponseDtoPage);
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
