package com.trillionares.tryit.review.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewDeleteResponseDto(
        UUID productId) {

    public static ReviewDeleteResponseDto from(UUID productId) {
        return ReviewDeleteResponseDto.builder()
                .productId(productId)
                .build();
    }
}
