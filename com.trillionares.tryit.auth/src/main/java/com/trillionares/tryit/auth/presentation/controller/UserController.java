package com.trillionares.tryit.auth.presentation.controller;


import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.auth.application.dto.InfoByUsernameResponseDto;
import com.trillionares.tryit.auth.application.service.UserService;
import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.infrastructure.config.CustomUserDetails;
import com.trillionares.tryit.auth.presentation.dto.BaseResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public BaseResponse<UserResponseDto> signup(@RequestBody @Valid SignUpRequestDto reqDto) {
    return BaseResponse.of(201, HttpStatus.CREATED, "회원가입에 성공했습니다.",userService.signup(reqDto));
  }

  @PutMapping("/password")
  public BaseResponse updatePassword(@RequestBody @Valid PasswordUpdateReqDto reqDto,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.updatePassword(reqDto, userDetails.getUserId());

    return BaseResponse.of(201, HttpStatus.ACCEPTED, "비밀번호가 수정되었습니다.", null);
  }

  @PutMapping("/{userId}")
  public BaseResponse<UserResponseDto> updateUserInfo(@PathVariable("userId") UUID userId,
      @Valid @RequestBody UserInfoUpdateReqDto reqDto,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.updateUserInfo(userDetails.getUserId(), reqDto);

    UserResponseDto updatedUserInfo = userService.updateUserInfo(userDetails.getUserId(), reqDto);

    return BaseResponse.of(201, HttpStatus.ACCEPTED, "회원정보가 수정되었습니다.", updatedUserInfo);
  }

  @DeleteMapping("/{userId}")
  public BaseResponse deleteUser(@PathVariable("userId") UUID userId,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    userService.deleteUser(userDetails.getUserId());

    return BaseResponse.of(204, HttpStatus.NO_CONTENT, "회원탈퇴가 완료되었습니다.", null);
  }

  @GetMapping("/internals/username/{username}") // 내부통신용으로 인증제외됨
  public BaseResponse<InfoByUsernameResponseDto> getUserByUsername(@PathVariable("username") String username) {
    InfoByUsernameResponseDto resDto = userService.getUserByUsername(username);
    return BaseResponse.of(200, HttpStatus.OK, "사용자 정보 조회에 성공하였습니다.", resDto);
  }

  @GetMapping("/{userId}")
  public BaseResponse<UserResponseDto> getUser(@PathVariable("userId") UUID userId) {
    UserResponseDto resDto = userService.getUser(userId);
    return BaseResponse.of(200, HttpStatus.OK, "사용자가 조회되었습니다.", resDto);
  }

  @GetMapping("/internals/{userId}")  // 내부통신용으로 인증제외됨
  public BaseResponse<UserResponseDto> getInternalUser(@PathVariable("userId") UUID userId) {
    UserResponseDto resDto = userService.getInternalUser(userId);
    return BaseResponse.of(200, HttpStatus.OK, "사용자가 조회되었습니다.", resDto);
  }

  @GetMapping("/userlists")
  public BaseResponse<PagedModel<UserResponseDto>> getUsers(
      @RequestParam(required = false) List<UUID> uuidList,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) Role role,
      @RequestParam(required = false) LocalDate startDate, //우선은 사용자 편의를 위해 시간제외하고 받음
      @RequestParam(required = false) LocalDate endDate,  //우선은 사용자 편의를 위해 시간제외하고 받음
      @RequestParam(required = false) Predicate predicate,
      Pageable pageable) {

    // LocalDate -> LocalDateTime 변환
    LocalDateTime startDateTime = (startDate != null)
        ? startDate.atStartOfDay()
        : null;

    LocalDateTime endDateTime = (endDate != null)
        ? endDate.atTime(23, 59, 59)
        : null;

    PagedModel<UserResponseDto> response = userService.getUsers(uuidList, username, email, role, startDateTime, endDateTime, predicate,
        pageable);
    return BaseResponse.of(200, HttpStatus.OK, "사용자 목록이 조회되었습니다.", response);
  }

  // 헤더 잘 추출 되고 잘 넣어지는지 테스트 용도
  @GetMapping("/test")
  public ResponseEntity<String> testHeaders(
      @RequestHeader(value = "X-Auth-Username", required = false) String username,
      @RequestHeader(value = "X-Auth-Role", required = false) String role) {

    // 헤더 값 로깅
    log.info("요청 헤더 - Username: {}, Role: {}", username, role);

    // 응답에 헤더 추가
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("X-Auth-Username", username);
    responseHeaders.add("X-Auth-Role", role);

    return ResponseEntity.ok()
        .headers(responseHeaders)
        .body("헤더 성공적으로 받아옴.");
  }

}
