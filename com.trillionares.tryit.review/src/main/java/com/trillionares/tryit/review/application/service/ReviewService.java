package com.trillionares.tryit.review.application.service;

import com.trillionares.tryit.review.application.dto.request.ReviewCreateRequestDto;
import com.trillionares.tryit.review.application.dto.request.ReviewUpdateRequestDto;
import com.trillionares.tryit.review.application.dto.response.*;
import com.trillionares.tryit.review.domain.client.AuthClient;
import com.trillionares.tryit.review.domain.client.TrialClient;
import com.trillionares.tryit.review.domain.model.Review;
import com.trillionares.tryit.review.domain.repository.ReviewRepository;
import com.trillionares.tryit.review.domain.service.ReviewValidation;
import com.trillionares.tryit.review.libs.exception.ErrorCode;
import com.trillionares.tryit.review.libs.exception.GlobalException;
import com.trillionares.tryit.review.presentation.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.trillionares.tryit.review.libs.exception.ErrorCode.REVIEW_CREATE_FORBIDDEN;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewValidation reviewValidation;
    private final TrialClient trialClient;
    private final AuthClient authClient;

    @Transactional
    public ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto, String role) {

        BaseResponse<ReviewIsSelectedResponseDto> reviewIsSelectedResponseDto
                = trialClient.isSelectedStatusOfTrial(reviewCreateRequestDto.submissionId());

        if(reviewValidation.isNotCreateValidation(role,reviewIsSelectedResponseDto.getData().getIsSelected()))
            throw new GlobalException(REVIEW_CREATE_FORBIDDEN);

        Review review = reviewRepository.save(Review.of(reviewCreateRequestDto.userId(),reviewCreateRequestDto.productId(),reviewCreateRequestDto.reviewTitle(),reviewCreateRequestDto.reviewContent(),reviewCreateRequestDto.reviewScore(),"image_url"));
        trialClient.notifySubmissionToTrial(reviewCreateRequestDto.submissionId());
        return ReviewCreateResponseDto.of(review.getReviewId(),review.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public ReviewGetResponseDto getReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(ErrorCode.REVIEW_ID_NOT_FOUND));
        return ReviewGetResponseDto.from(review);
    }

    @Transactional
    public ReviewUpdateResponseDto updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto, UUID reviewId, String role, String username) {

        BaseResponse<ReviewGetUserByUsernameResponseDto> reviewGetUserByUsernameResponseDto
                = authClient.getUserByUsernameOfUser(username);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(ErrorCode.REVIEW_ID_NOT_FOUND));

        if(reviewValidation.isNotUpdateValidation(role,review.getUserId(),reviewGetUserByUsernameResponseDto.getData().getUserId()))
            throw new GlobalException(ErrorCode.REVIEW_UPDATE_FORBIDDEN);

        review.update(reviewUpdateRequestDto.reviewTitle(),reviewUpdateRequestDto.reviewContent(),reviewUpdateRequestDto.reviewScore(),reviewUpdateRequestDto.reviewImgUrl());
        return ReviewUpdateResponseDto.from(review);
    }

    @Transactional
    public ReviewDeleteResponseDto deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
        return ReviewDeleteResponseDto.from(reviewId);
    }
}
