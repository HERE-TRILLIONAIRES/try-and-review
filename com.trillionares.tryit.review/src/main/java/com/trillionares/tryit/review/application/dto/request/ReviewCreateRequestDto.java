package com.trillionares.tryit.review.application.dto.request;

import com.trillionares.tryit.review.presentation.dto.ReviewCreateRequest;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewCreateRequestDto(
        UUID userId,
        UUID productId,
        String reviewTitle,
        String reviewContent,
        int reviewScore) {

    public static ReviewCreateRequestDto from(ReviewCreateRequest reviewCreateRequest) {
        return ReviewCreateRequestDto.builder()
                .userId(reviewCreateRequest.getUserId())
                .productId(reviewCreateRequest.getProductId())
                .reviewTitle(reviewCreateRequest.getReviewTitle())
                .reviewContent(reviewCreateRequest.getReviewContent())
                .reviewScore(reviewCreateRequest.getReviewScore())
                .build();
    }
}