package com.trillionares.tryit.trial.jhtest.presentation.controller;

import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.domain.service.TrialService;
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
import org.springframework.web.bind.annotation.RequestBody;
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
            @RequestBody TrialInfoRequestDto requestDto
    ) {
        try{
            TrialIdResponseDto responseDto = trialService.createTrial(requestDto);

            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, "신청을 완료했습니다.", responseDto);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @GetMapping("/{submissionId}")
    public BaseResponseDto<TrialInfoResponseDto> getTrialById(
            @PathVariable("submissionId") UUID submissionId
    ) {
        try {

            TrialInfoResponseDto responseDto = trialService.getTrialById(submissionId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 조회했습니다.", responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @PatchMapping("/{submissionId}")
    public BaseResponseDto<TrialIdResponseDto> changeStatusOfTrial(
            @PathVariable("submissionId") UUID submissionId,
            @RequestParam("status") SubmissionStatus status
    ) {
        try {
            TrialIdResponseDto responseDto = trialService.changeStatusOfTrial(submissionId, status);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 변경했습니다. 신청 상태 : " + status, responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }

    @DeleteMapping("/{submissionId}")
    public BaseResponseDto<TrialIdResponseDto> deleteTrial(
            @PathVariable("submissionId") UUID submissionId
    ) {
        try {
            TrialIdResponseDto responseDto = trialService.deleteTrial(submissionId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, "신청을 취소했습니다.", responseDto);

        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "실행 중 오류", null);
        }
    }
}
