package com.trillionares.tryit.review.application.dto.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewGetUserByUsernameResponseDto {

    private UUID userId;
    private String username;
    private String role;
    private String slackId;
}
