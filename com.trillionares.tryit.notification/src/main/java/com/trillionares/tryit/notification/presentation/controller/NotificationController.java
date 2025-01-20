package com.trillionares.tryit.notification.presentation.controller;

import com.trillionares.tryit.notification.application.dto.response.NotificationResponse;
import com.trillionares.tryit.notification.application.service.NotificationService;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {

  private final NotificationService notificationService;

  @GetMapping("/{notificationId}")
  public ResponseEntity<NotificationResponse> getNotification(
      @PathVariable UUID notificationId,
      @RequestHeader("X-Auth-Role") String role,
      @RequestHeader("X-Auth-Username") String username
  ) {

    NotificationResponse response = notificationService.getNotification(notificationId, role, username);

    return ResponseEntity.ok(response);
  }


  @GetMapping
  public ResponseEntity<Page<NotificationResponse>> searchNotifications(
      @RequestParam(required = false) NotificationStatus status,
      @RequestHeader("X-Auth-Role") String role,        // 헤더로 role 받기
      @RequestHeader("X-Auth-Username") String username,
      @PageableDefault(size = 20, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

    Page<NotificationResponse> notifications =
        notificationService.getNotificationByStatus(status, role, username, pageable);

    return notifications.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(notifications); // 조회 결과 있을때만 보여주도록
  }
}



