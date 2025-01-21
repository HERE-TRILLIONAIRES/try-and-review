package com.trillionares.tryit.statistics.domain.client;

import com.trillionares.tryit.statistics.application.dto.response.StatisticsGetUserByUsernameResponseDto;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service")
public interface AuthClient {

    @GetMapping("/users/internals/username/{username}")
    BaseResponse<StatisticsGetUserByUsernameResponseDto> getUserByUsernameOfUser(@PathVariable("username") String username);
}