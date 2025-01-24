package com.trillionares.tryit.review.application.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewStatisticsDataResponseDto {

        private final UUID productId;
        private final Integer highestScore;
        private final Integer lowestScore;
        private final Double averageScore;
        private final Integer reviewCount;

    @QueryProjection
    public ReviewStatisticsDataResponseDto(UUID productId, Integer highestScore, Integer lowestScore, Double averageScore, Integer reviewCount) {
        this.productId = productId;
        this.highestScore = highestScore;
        this.lowestScore = lowestScore;
        this.averageScore = averageScore;
        this.reviewCount = reviewCount;
    }
}
