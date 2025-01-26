package com.trillionares.tryit.trial.jhtest.presentation.controller;

import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.domain.service.TrialService;
import com.trillionares.tryit.trial.jhtest.presentation.dto.SubmissionIdAndStatusResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.base.BaseResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.trial.TrialInfoResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submissions")
public class trialController {

    private final TrialService trialService;

    @PostMapping()
    public BaseResponseDto<TrialIdResponseDto> createTrial(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @RequestBody TrialInfoRequestDto requestDto
    ) {
        try{
            TrialIdResponseDto responseDto = trialService.createTrial(username, role, requestDto);

            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, "신청을 완료했습니다.", responseDto);
        } catch (IllegalArgumentException ie) {
          return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ie.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @PostMapping("/enhanced")
    public BaseResponseDto<TrialIdResponseDto> enhancedCreateTrial(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @RequestBody TrialInfoRequestDto requestDto
    ) {
        try{
            TrialIdResponseDto responseDto = trialService.enhancedCreateTrial(username, role, requestDto);

            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, "신청을 완료했습니다.", responseDto);
        } catch (IllegalArgumentException ie) {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ie.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @GetMapping("/{submissionId}")
    public BaseResponseDto<TrialInfoResponseDto> getTrialById(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @PathVariable("submissionId") UUID submissionId
    ) {
        try {

            TrialInfoResponseDto responseDto = trialService.getTrialById(username, role, submissionId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 조회했습니다.", responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @PatchMapping("/{submissionId}")
    public BaseResponseDto<TrialIdResponseDto> changeStatusOfTrial(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @PathVariable("submissionId") UUID submissionId,
            @RequestParam("status") SubmissionStatus status
    ) {
        try {
            TrialIdResponseDto responseDto = trialService.changeStatusOfTrial(username, role, submissionId, status);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 변경했습니다. 신청 상태 : " + status, responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @DeleteMapping("/{submissionId}")
    public BaseResponseDto<TrialIdResponseDto> deleteTrial(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @PathVariable("submissionId") UUID submissionId
    ) {
        try {
            TrialIdResponseDto responseDto = trialService.deleteTrial(username, role, submissionId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 취소했습니다.", responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @GetMapping("/{submissionId}/isSelected")
    public BaseResponseDto<SubmissionIdAndStatusResponseDto> validateIsSelected(
            @PathVariable("submissionId") UUID submissionId
    ) {
        SubmissionIdAndStatusResponseDto responseDto = trialService.validateIsSelected(submissionId);

        return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 선택한 경우.", responseDto);
    }

    @PutMapping("/{submissionId}/submitReview")
    public BaseResponseDto<TrialIdResponseDto> updateSubmissionStatusToReviewSubmit(
            @PathVariable("submissionId") UUID submissionId
    ) {
        try {
            TrialIdResponseDto responseDto = trialService.updateSubmissionStatusToReviewSubmit(submissionId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 전송했습니다.", responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }
}
