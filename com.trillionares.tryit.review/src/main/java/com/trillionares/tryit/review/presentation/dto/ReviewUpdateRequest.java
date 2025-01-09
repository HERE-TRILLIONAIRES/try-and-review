package com.trillionares.tryit.review.presentation.dto;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {

    private String reviewTitle;
    private String reviewContent;
    private int reviewScore;
    private String reviewImgUrl;
}
