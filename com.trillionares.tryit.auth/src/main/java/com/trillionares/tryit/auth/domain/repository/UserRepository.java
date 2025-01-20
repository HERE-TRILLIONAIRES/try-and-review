package com.trillionares.tryit.auth.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import com.trillionares.tryit.auth.domain.model.QUser;
import com.trillionares.tryit.auth.domain.model.User;
import com.trillionares.tryit.auth.presentation.dto.responseDto.UserResponseDto;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface UserRepository extends JpaRepository<User, UUID>,
    UserRepositoryCustom, QuerydslPredicateExecutor<User>,
    QuerydslBinderCustomizer<QUser> {

  boolean existsByUsernameAndIsDeletedFalse(String username);

  boolean existsByUserIdAndIsDeletedFalse(UUID userId);

  Optional<User> findByUsernameAndIsDeletedFalse(String username);

  Optional<User> findByUserIdAndIsDeletedFalse(UUID userId);


  @Override // Predicate의 조건을 수정: 문자 검색 시 'equals 조건' -> 'contains 조건'
  default void customize(QuerydslBindings querydslBindings, @NotNull QUser qUser) {
    querydslBindings.bind(String.class)
        .all((StringPath path, Collection<? extends String> values) -> {
          List<String> valueList = new ArrayList<>(values.stream().map(String::trim).toList());
          if (valueList.isEmpty()) {
            return Optional.empty();
          }
          BooleanBuilder booleanBuilder = new BooleanBuilder();
          for (String s : valueList) {
            booleanBuilder.or(path.containsIgnoreCase(s));
          }
          return Optional.of(booleanBuilder);
        });
  }

}
