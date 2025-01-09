package com.trillionares.tryit.auth.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@EnableJpaAuditing
@Configuration
public class JpaConfig implements AuditorAware<String> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<String> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated() || authentication.getName()
        .equals("anonymousUser")) {
      return Optional.empty();
    }
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    return Optional.of(userDetails.getUsername());
  }
}
