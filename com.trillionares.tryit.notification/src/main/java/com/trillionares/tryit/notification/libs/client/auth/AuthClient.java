package com.trillionares.tryit.notification.libs.client.auth;

import com.trillionares.tryit.notification.libs.client.config.InternalFeignClientConfig;
import com.trillionares.tryit.notification.presentation.dto.BaseResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "auth-service",
    configuration = InternalFeignClientConfig.class
)
public interface AuthClient {

  @GetMapping("/users/internals/{userId}")
  BaseResponse<FeignUserIdResponseDto> getUserId(@PathVariable("userId") UUID userId);

  @GetMapping("/users/internals/username/{username}")
  BaseResponse<FeignUsernameResponseDto> getUserByUsername(@PathVariable("username") String username);
}
