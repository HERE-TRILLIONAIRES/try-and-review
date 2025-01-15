package com.trillionares.tryit.trial.jhtest.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String username;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String slackId;
    private String role;
}
