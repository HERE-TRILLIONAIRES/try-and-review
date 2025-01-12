package com.trillionares.tryit.auth.presentation.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserInfoUpdateReqDto {

  @NotBlank
  @Size(min = 1, max = 30, message = "이름(실명)은 최소 1자 이상, 30자 이하여야 합니다.")
  private String fullname;

  @NotBlank
  @Email(message = "유효한 이메일 주소를 입력하세요.")
  private String email;

  @NotBlank
  @Pattern(regexp = "^[0-9]{3}[-][0-9]{3,4}[-][0-9]{4}$",
      message = "유효한 전화번호 형식이 아닙니다.")
  private String phoneNumber;

  private String slackId;

}
