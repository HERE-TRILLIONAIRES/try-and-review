package com.trillionares.tryit.notification.infrastructure.persistence;

import com.trillionares.tryit.notification.domain.model.Notification;
import com.trillionares.tryit.notification.domain.repository.NotificationRepositoryCustom;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 인터페이스
 */
public interface NotificationRepository extends
    JpaRepository<Notification, UUID>,
    NotificationRepositoryCustom {

  boolean existsByMessageId(String messageId);

}
