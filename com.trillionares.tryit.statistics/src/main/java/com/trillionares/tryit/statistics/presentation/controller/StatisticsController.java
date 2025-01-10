package com.trillionares.tryit.statistics.presentation.controller;

import com.trillionares.tryit.statistics.application.dto.request.StatisticsCreateRequestDto;
import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateResponseDto;
import com.trillionares.tryit.statistics.application.dto.response.StatisticsGetResponseDto;
import com.trillionares.tryit.statistics.application.service.StatisticsService;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import com.trillionares.tryit.statistics.presentation.dto.StatisticsCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/statistics")
    public BaseResponse<StatisticsCreateResponseDto> createReview(@RequestBody @Valid StatisticsCreateRequest statisticsCreateRequest) {
        return BaseResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED,"통계 생성 성공",statisticsService.createStatistics(StatisticsCreateRequestDto.from(statisticsCreateRequest)));
    }

    @GetMapping("/statistics")
    public BaseResponse<List<StatisticsGetResponseDto>> getAllStatistics() {
        return BaseResponse.of(HttpStatus.OK.value(), HttpStatus.OK,"통계 전체 조회",statisticsService.getAllStatistics());
    }

    @GetMapping("/statistics/{userId}")
    public BaseResponse<List<StatisticsGetResponseDto>> getStatistics(@PathVariable("userId") UUID userId) {
        return BaseResponse.of(HttpStatus.OK.value(), HttpStatus.OK,"통계 목록 조회",statisticsService.getStatistics(userId));
    }
}
