package com.trillionares.tryit.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN,
  COMPANY,
  MEMBER;

  public String getAuthority() { // 권한문자열 메서드입니다
    return "ROLE_" + name();
  }
}
