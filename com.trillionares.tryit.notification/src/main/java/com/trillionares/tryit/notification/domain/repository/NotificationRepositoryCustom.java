package com.trillionares.tryit.notification.domain.repository;

import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * QueryDSL 구현을 위한 CustomRepository 인터페이스
 */
public interface NotificationRepositoryCustom {

  Page<NotificationResponse> findByNotificationStatus(NotificationStatus status, Pageable pageable);

}
