package com.trillionares.tryit.notification.presentation.controller;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.application.service.NotificationService;
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

  private final NotificationService notificationService;

  @GetMapping("/{notificationId}")
  public ResponseEntity<NotificationResponse> getNotification(@PathVariable UUID notificationId) {

    NotificationResponse response = notificationService.getNotification(notificationId);

    return ResponseEntity.ok(response);
  }



}
