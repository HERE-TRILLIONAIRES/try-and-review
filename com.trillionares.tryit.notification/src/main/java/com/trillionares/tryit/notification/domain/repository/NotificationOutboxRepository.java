package com.trillionares.tryit.notification.domain.repository;

import com.trillionares.tryit.notification.domain.model.NotificationOutbox;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationOutboxRepository extends JpaRepository<NotificationOutbox, UUID> {

  List<NotificationOutbox> findByOutboxStatus(NotificationStatus status);
}
