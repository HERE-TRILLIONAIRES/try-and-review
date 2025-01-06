package com.trillionares.tryit.auth.presentation.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequestDto {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

}
