package com.trillionares.tryit.review.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewDeleteResponseDto(
        UUID reviewId) {

    public static ReviewDeleteResponseDto from(UUID reviewId) {
        return ReviewDeleteResponseDto.builder()
                .reviewId(reviewId)
                .build();
    }
}
