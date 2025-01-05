package com.trillionares.tryit.review.presentation.controller;

import com.trillionares.tryit.review.application.dto.request.ReviewCreateRequestDto;
import com.trillionares.tryit.review.application.service.ReviewService;
import com.trillionares.tryit.review.presentation.dto.BaseResponse;
import com.trillionares.tryit.review.presentation.dto.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public BaseResponse<ReviewCreateRequestDto> createReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return BaseResponse.of(201,HttpStatus.CREATED,"리뷰 생성 성공",reviewService.createReview(ReviewCreateRequestDto.from(reviewCreateRequest)));
    }
}
