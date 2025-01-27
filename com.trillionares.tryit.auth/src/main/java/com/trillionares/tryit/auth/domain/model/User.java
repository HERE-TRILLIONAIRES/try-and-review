package com.trillionares.tryit.auth.domain.model;

import com.trillionares.tryit.auth.domain.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "p_user", schema = "auth")
@Entity
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id", updatable = false, nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false)
  private String password;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String email;

  @Column(name = "slack_id", nullable = false)
  private String slackId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;


  public static User createUser(String username, String fullname, String password, String email, String phoneNumber, String slackId, Role role) {
    return User.builder()
        .username(username)
        .fullname(fullname)
        .password(password)
        .email(email)
        .phoneNumber(phoneNumber)
        .slackId(slackId)
        .role(role)
        .build();
  }

  public void updatePassword(String password) {
    this.password = password;
  }

  public void updateUserInfo(String fullname, String email, String phoneNumber, String slackId) {
    if (fullname != null && !fullname.isEmpty()) {
      this.fullname = fullname;
    }
    if (email != null && !email.isEmpty()) {
      this.email = email;
    }
    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      this.phoneNumber = phoneNumber;
    }
    if (slackId != null && !slackId.isEmpty()) {
      this.slackId = slackId;
    }
  }


}
