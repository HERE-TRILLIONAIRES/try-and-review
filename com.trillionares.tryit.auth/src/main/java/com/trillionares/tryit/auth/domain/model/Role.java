package com.trillionares.tryit.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN(Authority.ADMIN),
  COMPANY(Authority.COMPANY),
  MEMBER(Authority.MEMBER);

  private final String authority;

  public static class Authority {
    public static final String ADMIN = "ADMIN";
    public static final String COMPANY = "COMPANY";
    public static final String MEMBER = "MEMBER";
  }
}
