package com.trillionares.tryit.auth.presentation.controller;

import com.trillionares.tryit.auth.application.service.AuthService;
import com.trillionares.tryit.auth.presentation.dto.BaseResponse;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignInRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signin")
  public BaseResponse login(@RequestBody @Valid SignInRequestDto signInRequestDto,
      HttpServletResponse response) {
    String token = authService.login(signInRequestDto);

    String bearerToken = "Bearer " + token;
    // 응답 헤더에 토큰 추가
    response.setHeader("Authorization", bearerToken);
    // data로도 토큰값 반환하도록 반환값 추가
    Map<String, String> responseData = Map.of("token", bearerToken);

    // BaseResponse 객체 생성 후 반환
    return BaseResponse.of(200, HttpStatus.OK, "로그인 성공", responseData);
  }

}


