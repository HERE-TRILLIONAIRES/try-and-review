package com.trillionares.tryit.statistics.domain.client;

import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateDataResponseDto;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="review-service")
public interface ReviewClient {

    @GetMapping("/reviews/statistics")
    BaseResponse<List<StatisticsCreateDataResponseDto>> getStatisticsDataToReview();
}