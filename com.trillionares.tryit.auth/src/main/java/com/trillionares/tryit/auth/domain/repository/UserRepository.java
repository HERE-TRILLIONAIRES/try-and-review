package com.trillionares.tryit.auth.domain.repository;

import com.trillionares.tryit.auth.domain.model.User;
import com.trillionares.tryit.auth.infrastructure.persistence.UserRepositoryCustom;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface UserRepository extends JpaRepository<User, UUID>{
  Optional<User> findByNickname(String nickname);

  Optional<User> findByEmail(String email);

  boolean existsByNickname(String nickname);

  boolean existsByEmail(String email);

}
