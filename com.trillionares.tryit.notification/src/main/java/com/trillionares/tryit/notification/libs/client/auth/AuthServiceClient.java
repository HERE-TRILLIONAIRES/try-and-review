package com.trillionares.tryit.notification.libs.client.auth;

import com.trillionares.tryit.notification.presentation.dto.BaseResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

  @GetMapping("/users/{userId}")
  BaseResponse<UserResponseDto> getUser(
  @PathVariable UUID userId,
  @RequestHeader("X-Username") String h_username,
  @RequestHeader("X-Role") String role);
}
