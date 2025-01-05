package com.trillionares.tryit.auth.presentation.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignUpRequestDto {

  @NotBlank
  @Size(min = 1, max = 30, message = "이름은 최소 1자 이상, 30자 이하여야 합니다.")
  private String username;

  @NotBlank
  @Size(min = 1, max = 30, message = "닉네임은 최소 1자 이상, 30자 이하여야 합니다.")
  private String nickname;

  @NotBlank
  @Email(message = "유효한 이메일 주소를 입력하세요.")
  private String email;

  @NotBlank
  @Size(min = 8, max = 20, message = "비밀번호은 최소 8자 이상, 20자 이하여야 합니다.")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
      message = "비밀번호는 영어 대소문자, 숫자, 특수문자를 포함해야 합니다.")
  private String password;

  @NotBlank
  private String role;

  @NotBlank
  @Pattern(regexp = "^[0-9]{3}[-][0-9]{3,4}[-][0-9]{4}$",
      message = "유효한 전화번호 형식이 아닙니다.")
  private String phoneNumber;

  private String slackId;

}
