package com.trillionares.tryit.review.application.dto.response;

import com.trillionares.tryit.review.domain.model.Review;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewGetResponseDto(
        UUID userId,
        UUID productId,
        String reviewTitle,
        String reviewContent,
        int reviewScore,
        String reviewImgUrl) {

    public static ReviewGetResponseDto from(Review review) {
        return ReviewGetResponseDto.builder()
                .userId(review.getUserId())
                .productId(review.getProductId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .reviewScore(review.getReviewScore())
                .reviewImgUrl(review.getReviewImgUrl())
                .build();
    }
}
