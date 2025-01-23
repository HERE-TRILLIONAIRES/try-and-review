package com.trillionares.tryit.statistics.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record StatisticsGetResponseDto(
        UUID statisticsId,
        UUID userId,
        UUID productId,
        Integer highestScore,
        Integer lowestScore,
        Double averageScore,
        Integer reviewCount,
        Long durationTime,
        LocalDateTime createdAt) {

    public static StatisticsGetResponseDto of(UUID statisticsId, UUID userId, UUID productId, Integer highestScore, Integer lowestScore, Double averageScore ,Integer reviewCount, Long durationTime, LocalDateTime createdAt) {
        return StatisticsGetResponseDto.builder()
                .statisticsId(statisticsId)
                .userId(userId)
                .productId(productId)
                .highestScore(highestScore)
                .lowestScore(lowestScore)
                .averageScore(averageScore)
                .reviewCount(reviewCount)
                .durationTime(durationTime)
                .createdAt(createdAt)
                .build();
    }
}
