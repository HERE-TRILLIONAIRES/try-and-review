package com.trillionares.tryit.auth.presentation.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordUpdateReqDto {

  private String currentPassword;

  @NotBlank
  @Size(min = 8, max = 20, message = "비밀번호은 최소 8자 이상, 20자 이하여야 합니다.")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
      message = "비밀번호는 영어 대소문자, 숫자, 특수문자를 포함해야 합니다.")
  private String newPassword;

}
