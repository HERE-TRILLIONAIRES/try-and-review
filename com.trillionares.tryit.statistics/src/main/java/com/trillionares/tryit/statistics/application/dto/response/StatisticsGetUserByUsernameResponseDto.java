package com.trillionares.tryit.statistics.application.dto.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class StatisticsGetUserByUsernameResponseDto {

    private UUID userId;
    private String username;
    private String role;
    private String slackId;
}
