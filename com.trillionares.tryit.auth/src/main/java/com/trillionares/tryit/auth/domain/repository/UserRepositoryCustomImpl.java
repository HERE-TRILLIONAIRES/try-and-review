package com.trillionares.tryit.auth.domain.repository;

import com.trillionares.tryit.auth.domain.model.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trillionares.tryit.auth.domain.model.Role;
import com.trillionares.tryit.auth.presentation.dto.responseDto.UserResponseDto;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  public UserRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public Page<UserResponseDto> findAllByConditions(
      List<UUID> userIdList, String username, String email, Role role,
      LocalDateTime startDateTime, LocalDateTime endDateTime, Predicate predicate, Pageable pageable
  ) {

    QUser user = QUser.user;

    BooleanBuilder builder = new BooleanBuilder(predicate);

    // userIdList가 비어있지 않으면 해당 ID들만 조회
    if (userIdList != null && !userIdList.isEmpty()) {
      builder.and(user.userId.in(userIdList));
    }

    // username 조건
    if (username != null && !username.isEmpty()) {
      builder.and(user.username.containsIgnoreCase(username));
    }

    // email 조건
    if (userIdList != null && !email.isEmpty()) {
      builder.and(user.email.containsIgnoreCase(email));
    }

    // role 조건
    if (role != null) {
      builder.and(user.role.eq(role));
    }

    // 날짜 조건
    if (startDateTime != null && endDateTime != null) {
      builder.and(user.createdAt.between(startDateTime, endDateTime));
    }

    // isDeleted가 false인 사용자만 조회
    builder.and(user.isDeleted.eq(false));

    // 페이징 처리: size를 10, 30, 50 중 하나로 고정
    int size = pageable.getPageSize();
    size = (size == 30 || size == 50) ? size : 10;
    pageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());

    // 정렬 조건이 없으면 기본값 설정
    Sort sort = pageable.getSort().isSorted() ? pageable.getSort() : Sort.by(
        Sort.Order.desc("createdAt"),
        Sort.Order.desc("updatedAt")
    );

    // 사용자 목록 조회
    List<UserResponseDto> results = queryFactory
        .select(Projections.constructor(UserResponseDto.class,
            user.userId,
            user.username,
            user.fullname,
            user.email,
            user.phoneNumber,
            user.slackId,
            user.role))
        .from(user)
        .where(builder)
        .orderBy(getDynamicSort(sort, user.getType(), user.getMetadata()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    // 총 사용자 수 조회
    Long total = queryFactory
        .select(user.count())
        .from(user)
        .where(builder)
        .fetchOne();

    if (total == null) {
      total = 0L;
    }

    return new PageImpl<>(results, pageable, total);
  }

  // 동적 정렬 로직은 그대로 재사용
  private <T> OrderSpecifier[] getDynamicSort(Sort sort, Class<? extends T> entityClass,
      PathMetadata pathMetadata) {
    List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
    PathBuilder<Object> pathBuilder = new PathBuilder<>(entityClass, pathMetadata);

    sort.stream().forEach(orderSpecifier -> {
      Order direction = orderSpecifier.isAscending() ? Order.ASC : Order.DESC;
      String prop = orderSpecifier.getProperty();
      orderSpecifiers.add(new OrderSpecifier(direction, pathBuilder.get(prop)));
    });

    return orderSpecifiers.toArray(new OrderSpecifier[0]);
  }

}
