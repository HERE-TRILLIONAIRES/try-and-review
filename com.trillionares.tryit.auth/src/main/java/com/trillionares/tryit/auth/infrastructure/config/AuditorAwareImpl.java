package com.trillionares.tryit.auth.infrastructure.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
      CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
      return Optional.of(customUserDetails.getUsername());
    }

    return Optional.of("SYSTEM");
  }
}
