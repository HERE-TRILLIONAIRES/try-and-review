package com.trillionares.tryit.review.domain.service;

import org.springframework.stereotype.Service;

@Service
public class ReviewValidation {

    private boolean isAdmin(String role) {
        return "ROLE_ADMIN".equals(role);
    }

    private boolean isMember(String role) {
        return "ROLE_MEMBER".equals(role);
    }
}