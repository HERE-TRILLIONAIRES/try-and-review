package com.trillionares.tryit.review.application.dto.response;

import com.trillionares.tryit.review.domain.model.Review;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewUpdateResponseDto(
        UUID reviewId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ReviewUpdateResponseDto from(Review review) {
        return ReviewUpdateResponseDto.builder()
                .reviewId(review.getReviewId())
                .createdAt(review.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
