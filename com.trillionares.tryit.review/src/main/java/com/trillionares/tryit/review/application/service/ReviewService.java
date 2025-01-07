package com.trillionares.tryit.review.application.service;

import com.trillionares.tryit.review.application.dto.request.ReviewCreateRequestDto;
import com.trillionares.tryit.review.application.dto.response.ReviewCreateResponseDto;
import com.trillionares.tryit.review.domain.model.Review;
import com.trillionares.tryit.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = reviewRepository.save(Review.of(reviewCreateRequestDto.userId(),reviewCreateRequestDto.productId(),reviewCreateRequestDto.reviewTitle(),reviewCreateRequestDto.reviewContent(),reviewCreateRequestDto.reviewScore(),"image_url"));
        return ReviewCreateResponseDto.of(review.getReviewId(),review.getCreatedAt());
    }
}
