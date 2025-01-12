package com.trillionares.tryit.review.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewUpdateRequest {

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewContent;

    @NotNull @Min(value = 1) @Max(value = 5)
    private Integer reviewScore;

    @NotBlank
    private String reviewImgUrl;
}
