package com.trillionares.tryit.statistics.presentation.controller;

import com.trillionares.tryit.statistics.application.dto.request.StatisticsCreateRequestDto;
import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateResponseDto;
import com.trillionares.tryit.statistics.application.service.StatisticsService;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import com.trillionares.tryit.statistics.presentation.dto.StatisticsCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/statistics")
    public BaseResponse<StatisticsCreateResponseDto> createReview(@RequestBody StatisticsCreateRequest statisticsCreateRequest) {
        return BaseResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED,"통계 생성 성공",statisticsService.createStatistics(StatisticsCreateRequestDto.from(statisticsCreateRequest)));
    }
}
