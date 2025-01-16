package com.trillionares.tryit.trial.jhtest.domain.client;

import com.trillionares.tryit.trial.jhtest.infrastructure.config.FeignConfig;
import com.trillionares.tryit.trial.jhtest.presentation.dto.InfoByUsernameResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.UserResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.base.BaseResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service", configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping("/users/internals/username/{username}")
    BaseResponseDto<InfoByUsernameResponseDto> getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/users/internals/{userId}")
    BaseResponseDto<UserResponseDto> getUserInfo(@PathVariable("userId") UUID userId);
}
