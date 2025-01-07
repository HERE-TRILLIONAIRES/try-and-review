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
    return name();
  }

  @Override
  public String toString() {
    return "ROLE_" + name(); // Enum의 이름을 "ROLE_" 접두사와 함께 반환
  }
}
