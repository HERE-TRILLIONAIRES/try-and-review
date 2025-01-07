package com.trillionares.tryit.trial.jhtest.presentation.controller;

import com.trillionares.tryit.trial.jhtest.domain.service.TrialService;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.base.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trials")
public class trialController {

    private final TrialService trialService;

    @PostMapping()
    public BaseResponseDto<TrialIdResponseDto> createTrial(@RequestBody TrialInfoRequestDto requestDto) {

        TrialIdResponseDto responseDto = trialService.createTrial(requestDto);

        return null;
    }

}
