package com.trillionares.tryit.auth.presentation.controller;


import com.trillionares.tryit.auth.application.service.UserService;
import com.trillionares.tryit.auth.presentation.dto.BaseResponse;
import com.trillionares.tryit.auth.presentation.dto.requestDto.SignUpRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public BaseResponse signup(@RequestBody @Valid SignUpRequestDto reqDto) {
    return BaseResponse.of(201, HttpStatus.CREATED, "회원가입에 성공했습니다.",userService.signup(reqDto));
  }

}
