package com.trillionares.tryit.trial.jhtest.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoByUsernameResponseDto {
    private UUID userId;
    private String username;
    private String role;
    private String slackId;
}
