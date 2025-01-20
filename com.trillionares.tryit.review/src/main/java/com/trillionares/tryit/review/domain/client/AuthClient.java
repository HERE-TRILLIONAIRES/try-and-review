package com.trillionares.tryit.review.domain.client;

import com.trillionares.tryit.review.application.dto.response.ReviewGetUserByUsernameResponseDto;
import com.trillionares.tryit.review.presentation.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service")
public interface AuthClient {

    @GetMapping("/users/internals/username/{username}")
    BaseResponse<ReviewGetUserByUsernameResponseDto> getUserByUsernameOfUser(@PathVariable("username") String username);
}