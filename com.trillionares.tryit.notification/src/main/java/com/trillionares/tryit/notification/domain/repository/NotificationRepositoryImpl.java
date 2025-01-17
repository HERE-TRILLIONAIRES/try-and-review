package com.trillionares.tryit.notification.domain.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trillionares.tryit.notification.application.dto.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.domain.model.QNotification;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * 인터페이스 구현체
 */
@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QNotification notification = QNotification.notification;

  public Page<NotificationResponse> findByNotificationStatus(NotificationStatus status, Pageable pageable) {

    List<NotificationResponse> content = queryFactory
        .select(Projections.constructor(NotificationResponse.class,
            notification.notificationId,
            notification.userId,
            notification.submissionId,
            notification.notificationStatus,
            notification.attemptCount,
            notification.createdAt,
            notification.createdBy))
        .from(notification)
        .where(notification.notificationStatus.eq(status))// 상태가 일치하는 것만
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(notification.createdAt.desc())
        .fetch();

    long total = Optional.ofNullable(queryFactory
            .select(notification.count())
            .from(notification)
            .where(notification.notificationStatus.eq(status))
            .fetchOne())
        .orElse(0L);

    return new PageImpl<>(content, pageable, total);
  }

  public Page<NotificationResponse> findByNotificationStatusAndUserId(
      NotificationStatus status,
      UUID userId,
      Pageable pageable) {

    List<NotificationResponse> content = queryFactory
        .select(Projections.constructor(NotificationResponse.class,
            notification.notificationId,
            notification.userId,
            notification.submissionId,
            notification.notificationStatus,
            notification.attemptCount,
            notification.createdAt,
            notification.createdBy))
        .from(notification)
        .where(notification.notificationStatus.eq(status),
            notification.userId.eq(userId)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(notification.createdAt.desc())
        .fetch();

    long total = Optional.ofNullable(queryFactory
            .select(notification.count())
            .from(notification)
            .where(notification.notificationStatus.eq(status),
            notification.userId.eq(userId))
            .fetchOne())
        .orElse(0L);

    return new PageImpl<>(content, pageable, total);
  }
}
