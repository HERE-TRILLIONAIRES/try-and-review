package com.trillionares.tryit.notification.application.service;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class NotificationRoleValidation {

  boolean isAdmin(String role) {
    return "ROLE_ADMIN".equals(role);
  }

  private boolean isMember(String role) {
    return "ROLE_MEMBER".equals(role);
  }

  boolean isNotificationOwner(UUID notificationUserId, UUID currentUserId) {
    return notificationUserId.equals(currentUserId);
  }
}
