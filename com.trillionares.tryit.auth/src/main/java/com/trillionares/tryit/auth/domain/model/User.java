package com.trillionares.tryit.auth.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String nickname;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String slackId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  private Boolean isDeleted;

}
