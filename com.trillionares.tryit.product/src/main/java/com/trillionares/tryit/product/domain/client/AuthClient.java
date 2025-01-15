package com.trillionares.tryit.product.domain.client;

import com.trillionares.tryit.product.infrastructure.config.FeignConfig;
import com.trillionares.tryit.product.presentation.dto.InfoByUsernameResponseDto;
import com.trillionares.tryit.product.presentation.dto.common.base.BaseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service", configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping("/users/internals/username/{username}")
    BaseResponseDto<InfoByUsernameResponseDto> getUserByUsername(@PathVariable("username") String username);
}
