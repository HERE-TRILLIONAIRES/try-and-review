package com.trillionares.tryit.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN,
  COMPANY,
  MEMBER;
}
