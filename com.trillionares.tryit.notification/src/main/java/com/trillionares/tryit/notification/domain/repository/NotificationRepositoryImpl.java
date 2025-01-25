package com.trillionares.tryit.notification.domain.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trillionares.tryit.notification.application.dto.response.NotificationResponse;
import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import com.trillionares.tryit.notification.domain.model.QNotification;
import java.time.LocalDateTime;
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

  /**
   *
   * @param status 알림 상태
   * @param userId 사용자 userId(PK값)
   * @param startDate 조회 시작 일자
   * @param endDate 조회 종료 일자
   * @param pageable 페이징 정보(페이지 크기, 정렬 조건)
   * @return 검색 조건에 맞는 조회 목록 반환
   */
  public Page<NotificationResponse> findBySearch(
      NotificationStatus status,
      UUID userId,
      LocalDateTime startDate,
      LocalDateTime endDate,
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
            userId != null ? notification.userId.eq(userId) : null,
            dateRange(startDate, endDate))
        .offset(pageable.getOffset()) // 페이지 * 사이즈
        .limit(pageable.getPageSize()) // 한 페이지당 개수
        .orderBy(notification.createdAt.desc())
        .fetch();

    long total = Optional.ofNullable(queryFactory
            .select(notification.count())
            .from(notification)
            .where(notification.notificationStatus.eq(status),
                userId != null ? notification.userId.eq(userId) : null,
                startDate != null && endDate != null ?
                    notification.createdAt.between(startDate, endDate) : null)
            .fetchOne())
        .orElse(0L);

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression dateRange(LocalDateTime startDate, LocalDateTime endDate) {
    return startDate != null && endDate != null ?
        notification.createdAt.between(startDate, endDate) : null;
  }

  /**
   * 상태에 따라 조회하는 메서드
   * @param status 알림 상태
   * @param pageable 페이징 정보(페이지 크기, 정렬 조건)
   * @return 조회 조건(상태)에 맞는 목록 반환
   */
  public Page<NotificationResponse> findByNotificationStatus(NotificationStatus status,
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

  /**
   * 특정 사용자의 알림만 조회할 수 있는 메서드
   * @param status 알림 상태
   * @param userId 사용자의 userId(PK값)
   * @param pageable 페이징 정보(페이지 크기, 정렬 조건)
   * @return 특정 사용자의 알림으로 조회한 목록 결과
   */
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

