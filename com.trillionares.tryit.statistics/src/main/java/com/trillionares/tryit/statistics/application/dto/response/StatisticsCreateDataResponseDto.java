package com.trillionares.tryit.statistics.application.dto.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class StatisticsCreateDataResponseDto {

    private UUID productId;
    private Integer highestScore;
    private Integer lowestScore;
    private Double averageScore;
    private Integer reviewCount;
}
