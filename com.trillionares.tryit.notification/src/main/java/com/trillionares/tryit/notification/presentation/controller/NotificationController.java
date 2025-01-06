package com.trillionares.tryit.notification.presentation.controller;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.repository.NotificationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {

  private final NotificationRepository notificationRepository;

  @GetMapping("/{notificationId}")
  public ResponseEntity<NotificationResponse> getNotification(@PathVariable UUID notificationId) {

    return notificationRepository.findById(notificationId) // 테스트용 임의 응답
        .map(notification -> ResponseEntity.ok(NotificationResponse.from(notification)))
        .orElse(ResponseEntity.notFound().build());
  }



}
