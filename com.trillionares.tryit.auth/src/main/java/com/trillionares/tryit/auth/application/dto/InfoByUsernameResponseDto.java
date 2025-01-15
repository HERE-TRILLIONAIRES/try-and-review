package com.trillionares.tryit.auth.application.dto;

import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.domain.model.User;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InfoByUsernameResponseDto {

  private UUID userId;
  private String username;
  private Role role;
  private String slackId;

  public InfoByUsernameResponseDto(User user) {
    this.userId = user.getUserId();
    this.username = user.getUsername();
    this.slackId = user.getSlackId();
    this.role = user.getRole();
  }

}
