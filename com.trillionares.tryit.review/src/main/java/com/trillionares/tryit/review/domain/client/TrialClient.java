package com.trillionares.tryit.review.domain.client;

import com.trillionares.tryit.review.application.dto.response.ReviewIsSelectedResponseDto;
import com.trillionares.tryit.review.application.dto.response.ReviewSubmitResponseDto;
import com.trillionares.tryit.review.presentation.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name="recruitment-service")
public interface TrialClient {

    @GetMapping("/api/submissions/{submissionId}/isSelected")
    BaseResponse<ReviewIsSelectedResponseDto> isSelectedStatusOfTrial(@PathVariable("submissionId") UUID submissionId);

    @PutMapping("/api/submissions/{submissionId}/submitReview")
    BaseResponse<ReviewSubmitResponseDto> notifySubmissionToTrial(@PathVariable("submissionId") UUID submissionId);
}