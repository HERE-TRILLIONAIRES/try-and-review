package com.trillionares.tryit.statistics.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StatisticsCreateRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID productId;

    @NotNull @Min(value = 1) @Max(value = 5)
    private Integer highestScore;

    @NotNull @Min(value = 1) @Max(value = 5)
    private Integer lowestScore;

    @NotNull @PositiveOrZero
    private Integer reviewCount;

    @NotNull @Positive
    private Long durationTime;
}
