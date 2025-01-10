package com.trillionares.tryit.statistics.presentation.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class StatisticsCreateRequest {

    private UUID userId;
    private UUID productId;
    private Integer highestScore;
    private Integer lowestScore;
    private Integer reviewCount;
    private Long durationTime;
}
