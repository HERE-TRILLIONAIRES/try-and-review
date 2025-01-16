package com.trillionares.tryit.review.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewCreateRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID productId;

    @NotNull
    private UUID submissionId;

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewContent;

    @NotNull @Min(value=1) @Max(value=5)
    private Integer reviewScore;
}