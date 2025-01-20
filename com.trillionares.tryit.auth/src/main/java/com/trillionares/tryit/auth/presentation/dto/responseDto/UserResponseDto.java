package com.trillionares.tryit.auth.presentation.dto.responseDto;

import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.domain.model.User;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto implements Serializable {
  private UUID userId;
  private String username;
  private String fullname;
  private String email;
  private String phoneNumber;
  private String slackId;
  private Role role;

  public UserResponseDto(User user) {
    this.userId = user.getUserId();
    this.username = user.getUsername();
    this.fullname = user.getFullname();
    this.email = user.getEmail();
    this.phoneNumber = user.getPhoneNumber();
    this.slackId = user.getSlackId();
    this.role = user.getRole();
  }

}
