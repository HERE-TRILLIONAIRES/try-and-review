package com.trillionares.tryit.auth.domain.repository;

import com.trillionares.tryit.auth.domain.model.User;
import com.trillionares.tryit.auth.infrastructure.persistence.UserRepositoryCustom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface UserRepository extends JpaRepository<User, UUID>{

  Optional<User> findByUsername(String username);

  boolean existsByUsernameAndIsDeletedFalse(
      @NotBlank @Size(min = 1, max = 30, message = "이름은 최소 1자 이상, 30자 이하여야 합니다.") String username);

  Optional<User> findByIdAndIsDeletedFalse(UUID userId);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);


}
