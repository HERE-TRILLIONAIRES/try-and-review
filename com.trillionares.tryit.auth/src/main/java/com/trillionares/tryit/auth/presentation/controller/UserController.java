package com.trillionares.tryit.auth.presentation.controller;


import com.trillionares.tryit.auth.application.service.UserService;
import com.trillionares.tryit.auth.infrastructure.config.CustomUserDetails;
import com.trillionares.tryit.auth.presentation.dto.BaseResponse;
import com.trillionares.tryit.auth.presentation.dto.requestDto.PasswordUpdateReqDto;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignUpRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public BaseResponse signup(@RequestBody @Valid SignUpRequestDto reqDto) {
    return BaseResponse.of(201, HttpStatus.CREATED, "회원가입에 성공했습니다.",userService.signup(reqDto));
  }

  @PutMapping("/password")
  public BaseResponse updatePassword(@RequestBody @Valid PasswordUpdateReqDto reqDto,
      @AuthenticationPrincipal
      CustomUserDetails userDetails) {
    userService.updatePassword(reqDto, userDetails.getUserId());

    return BaseResponse.of(201, HttpStatus.ACCEPTED, "비밀번호가 수정되었습니다.", null);
  }

}
