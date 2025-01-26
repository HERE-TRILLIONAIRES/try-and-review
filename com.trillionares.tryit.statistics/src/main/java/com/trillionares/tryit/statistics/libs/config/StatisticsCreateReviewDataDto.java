package com.trillionares.tryit.statistics.libs.config;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record StatisticsCreateReviewDataDto(

        UUID productId,
        Integer highestScore,
        Integer lowestScore,
        Double averageScore,
        Integer reviewCount) {

    public static StatisticsCreateReviewDataDto of(UUID productId, Integer highestScore, Integer lowestScore, Double averageScore, Integer reviewCount) {
        return StatisticsCreateReviewDataDto.builder()
                .productId(productId)
                .highestScore(highestScore)
                .lowestScore(lowestScore)
                .averageScore(averageScore)
                .reviewCount(reviewCount)
                .build();
    }
}
