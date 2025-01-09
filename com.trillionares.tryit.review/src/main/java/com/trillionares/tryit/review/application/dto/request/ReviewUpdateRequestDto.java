package com.trillionares.tryit.review.application.dto.request;

import com.trillionares.tryit.review.presentation.dto.ReviewUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;


@Builder(access = AccessLevel.PRIVATE)
public record ReviewUpdateRequestDto(
        String reviewTitle,
        String reviewContent,
        int reviewScore,
        String reviewImgUrl) {

    public static ReviewUpdateRequestDto from(ReviewUpdateRequest reviewUpdateRequest) {
        return ReviewUpdateRequestDto.builder()
                .reviewTitle(reviewUpdateRequest.getReviewTitle())
                .reviewContent(reviewUpdateRequest.getReviewContent())
                .reviewScore(reviewUpdateRequest.getReviewScore())
                .reviewImgUrl(reviewUpdateRequest.getReviewImgUrl())
                .build();
    }
}
