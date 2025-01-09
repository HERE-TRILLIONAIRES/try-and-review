package com.trillionares.tryit.auth.application.dto;

import com.trillionares.tryit.auth.domain.model.Role;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthorityResponseDto {

  private UUID userId;
  private String username;
  private Role role;

}
