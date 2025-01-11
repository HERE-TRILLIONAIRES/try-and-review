package com.trillionares.tryit.notification.domain.repository;


import com.trillionares.tryit.notification.domain.model.Notification;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {

  Notification save(Notification notification);
  Optional<Notification> findById(UUID notificationId);

}
