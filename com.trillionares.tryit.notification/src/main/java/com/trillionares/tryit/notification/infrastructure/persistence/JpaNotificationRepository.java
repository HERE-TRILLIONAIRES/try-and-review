package com.trillionares.tryit.notification.infrastructure.persistence;

import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.repository.NotificationRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends NotificationRepository, JpaRepository<Notification, UUID> {

}
