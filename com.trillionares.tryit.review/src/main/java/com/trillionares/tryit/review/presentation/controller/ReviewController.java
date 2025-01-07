package com.trillionares.tryit.review.presentation.controller;

import com.trillionares.tryit.review.application.dto.request.ReviewCreateRequestDto;
import com.trillionares.tryit.review.application.dto.response.ReviewCreateResponseDto;
import com.trillionares.tryit.review.application.dto.response.ReviewGetResponseDto;
import com.trillionares.tryit.review.application.service.ReviewService;
import com.trillionares.tryit.review.presentation.dto.BaseResponse;
import com.trillionares.tryit.review.presentation.dto.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public BaseResponse<ReviewCreateResponseDto> createReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return BaseResponse.of(HttpStatus.CREATED.value(),HttpStatus.CREATED,"리뷰 생성 성공",
                reviewService.createReview(ReviewCreateRequestDto.from(reviewCreateRequest)));
    }

    @GetMapping("/reviews/{reviewId}")
    public BaseResponse<ReviewGetResponseDto> getReview(@PathVariable UUID reviewId) {
        return BaseResponse.of(HttpStatus.OK.value(),HttpStatus.OK,"리뷰 단건 조회 성공",reviewService.getReview(reviewId));
    }
}
