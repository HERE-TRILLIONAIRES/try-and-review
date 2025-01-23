package com.trillionares.tryit.statistics.domain.client;

import com.trillionares.tryit.statistics.application.dto.response.StatisticsGetProductInfoResponseDto;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="product-service")
public interface ProductClient {

    @GetMapping("/api/products/{productId}")
    BaseResponse<StatisticsGetProductInfoResponseDto> getProductInfoStatisticsToProduct(@PathVariable("userId") UUID productId);
}
