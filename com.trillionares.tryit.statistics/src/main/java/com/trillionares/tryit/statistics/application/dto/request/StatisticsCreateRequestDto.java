package com.trillionares.tryit.statistics.application.dto.request;

import com.trillionares.tryit.statistics.presentation.dto.StatisticsCreateRequest;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record StatisticsCreateRequestDto(
        UUID userId,
        UUID productId,
        Integer highestScore,
        Integer lowestScore,
        Double averageScore,
        Integer reviewCount,
        Long durationTime) {

    public static StatisticsCreateRequestDto from(StatisticsCreateRequest statisticsCreateRequest) {
        return StatisticsCreateRequestDto.builder()
                .userId(statisticsCreateRequest.getUserId())
                .productId(statisticsCreateRequest.getProductId())
                .highestScore(statisticsCreateRequest.getHighestScore())
                .lowestScore(statisticsCreateRequest.getLowestScore())
                .reviewCount(statisticsCreateRequest.getReviewCount())
                .durationTime(statisticsCreateRequest.getDurationTime())
                .build();
    }
}
