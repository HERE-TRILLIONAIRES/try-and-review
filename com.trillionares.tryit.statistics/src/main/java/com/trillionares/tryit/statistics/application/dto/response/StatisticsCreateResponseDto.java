package com.trillionares.tryit.statistics.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record StatisticsCreateResponseDto(
        UUID statisticsId,
        LocalDateTime createdAt) {

    public static StatisticsCreateResponseDto of(UUID statisticsId, LocalDateTime createdAt) {
        return StatisticsCreateResponseDto.builder()
                .statisticsId(statisticsId)
                .createdAt(createdAt)
                .build();
    }
}
