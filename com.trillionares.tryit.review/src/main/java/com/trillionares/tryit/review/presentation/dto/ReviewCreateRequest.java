package com.trillionares.tryit.review.presentation.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewCreateRequest {

    private UUID userId;
    private UUID productId;
    private String reviewTitle;
    private String reviewContent;
    private int reviewScore;
}