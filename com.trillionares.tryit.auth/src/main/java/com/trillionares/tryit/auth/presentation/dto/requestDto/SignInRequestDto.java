package com.trillionares.tryit.auth.presentation.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public class SignInRequestDto {

  @NotBlank
  private String nickname;

  @NotBlank
  private String password;

}
