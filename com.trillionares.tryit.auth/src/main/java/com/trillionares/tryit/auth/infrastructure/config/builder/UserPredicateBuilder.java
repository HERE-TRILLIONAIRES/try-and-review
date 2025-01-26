package com.trillionares.tryit.auth.infrastructure.config.builder;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.auth.domain.model.QUser;
import com.trillionares.tryit.auth.domain.model.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserPredicateBuilder {
  public static Predicate createPredicate(
      List<UUID> userIdList, String username, String email, Role role,
      LocalDateTime startDateTime, LocalDateTime endDateTime) {

    QUser user = QUser.user;
    BooleanBuilder builder = new BooleanBuilder();

    // userIdList 조건
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

    // isDeleted == false 조건 추가
    builder.and(user.isDeleted.eq(false));

    return builder;
  }

}
