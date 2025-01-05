package com.trillionares.tryit.review.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewCreateResponseDto(
        UUID reviewId,
        LocalDateTime createdAt) {

    public static ReviewCreateResponseDto of(UUID reviewId, LocalDateTime createdAt) {
        return ReviewCreateResponseDto.builder()
                .reviewId(reviewId)
                .createdAt(createdAt)
                .build();
    }
}
