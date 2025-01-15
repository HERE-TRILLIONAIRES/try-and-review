package com.trillionares.tryit.trial.jhtest.domain.client;

import com.trillionares.tryit.trial.jhtest.infrastructure.config.FeignConfig;
import com.trillionares.tryit.trial.jhtest.presentation.dto.RecruitmentExistAndStatusDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.base.BaseResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service", configuration = FeignConfig.class)
public interface RecruitmentClient {

    @GetMapping("/recruitments/isExist/{recruitmentId}")
    BaseResponseDto<RecruitmentExistAndStatusDto> isExistRecruitmentById(@PathVariable("recruitmentId") UUID recruitmentId);
}
