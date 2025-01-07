package com.trillionares.tryit.review.application.service;

import com.trillionares.tryit.review.application.dto.request.ReviewCreateRequestDto;
import com.trillionares.tryit.review.application.dto.request.ReviewUpdateRequestDto;
import com.trillionares.tryit.review.application.dto.response.ReviewCreateResponseDto;
import com.trillionares.tryit.review.application.dto.response.ReviewGetResponseDto;
import com.trillionares.tryit.review.application.dto.response.ReviewUpdateResponseDto;
import com.trillionares.tryit.review.domain.model.Review;
import com.trillionares.tryit.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = reviewRepository.save(Review.of(reviewCreateRequestDto.userId(),reviewCreateRequestDto.productId(),reviewCreateRequestDto.reviewTitle(),reviewCreateRequestDto.reviewContent(),reviewCreateRequestDto.reviewScore(),"image_url"));
        return ReviewCreateResponseDto.of(review.getReviewId(),review.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public ReviewGetResponseDto getReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return ReviewGetResponseDto.from(review);
    }

    @Transactional
    public ReviewUpdateResponseDto updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto, UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        review.update(reviewUpdateRequestDto.reviewTitle(),reviewUpdateRequestDto.reviewContent(),reviewUpdateRequestDto.reviewScore(),reviewUpdateRequestDto.reviewImgUrl());
        return ReviewUpdateResponseDto.from(review);
    }
}