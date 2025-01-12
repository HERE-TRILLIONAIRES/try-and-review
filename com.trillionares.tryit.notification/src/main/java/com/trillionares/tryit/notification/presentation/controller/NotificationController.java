package com.trillionares.tryit.notification.presentation.controller;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.application.service.NotificationService;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.infrastructure.messaging.event.SubmissionSelectedEvent;
import com.trillionares.tryit.notification.libs.exception.GlobalException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping("/test/event")
  public ResponseEntity<?> createNotification(@RequestBody SubmissionSelectedEvent event) {
    log.debug("Received submission event: {}", event);
    try {
      notificationService.createNotificationFromSubmissionEvent(event);
      return ResponseEntity.ok().build();
    } catch (GlobalException e) {
      log.error("Failed to process submission event: {}", e.getMessage());
      throw e;
    }
  }

  @GetMapping("/{notificationId}")
  public ResponseEntity<NotificationResponse> getNotification(@PathVariable UUID notificationId) {

    NotificationResponse response = notificationService.getNotification(notificationId);

    return ResponseEntity.ok(response);
  }


  @GetMapping
  public ResponseEntity<Page<NotificationResponse>> searchNotifications(
      @RequestParam(required = false) NotificationStatus status,
      @PageableDefault(size = 20, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

    Page<NotificationResponse> notifications = notificationService.getNotificationByStatus(status,
        pageable);

    return notifications.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(notifications); // 조회 결과 있을때만 보여주도록
  }
}



