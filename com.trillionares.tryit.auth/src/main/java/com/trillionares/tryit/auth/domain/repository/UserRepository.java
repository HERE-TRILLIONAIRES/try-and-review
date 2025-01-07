package com.trillionares.tryit.auth.domain.repository;

import com.trillionares.tryit.auth.domain.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID>{

  boolean existsByUsernameAndIsDeletedFalse(String username);

  boolean existsByUserIdAndIsDeletedFalse(UUID userId);

  Optional<User> findByUsernameAndIsDeletedFalse(String username);

  Optional<User> findByUserIdAndIsDeletedFalse(UUID userId);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);


}
