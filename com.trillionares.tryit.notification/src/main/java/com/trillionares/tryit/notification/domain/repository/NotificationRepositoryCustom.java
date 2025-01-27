package com.trillionares.tryit.notification.domain.repository;

import com.trillionares.tryit.notification.application.dto.response.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * QueryDSL 구현을 위한 CustomRepository 인터페이스
 */
public interface NotificationRepositoryCustom {

  Page<NotificationResponse> findByNotificationStatus(NotificationStatus status, Pageable pageable);

  Page<NotificationResponse> findByNotificationStatusAndUserId(NotificationStatus status,
      UUID userId, Pageable pageable);

  Page<NotificationResponse> findBySearch(NotificationStatus status, UUID userId,
      LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
