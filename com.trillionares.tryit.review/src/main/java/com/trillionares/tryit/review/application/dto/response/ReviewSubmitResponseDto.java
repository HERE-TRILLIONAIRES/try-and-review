package com.trillionares.tryit.review.application.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewSubmitResponseDto {

    @NotNull
    private UUID trialId;
}
