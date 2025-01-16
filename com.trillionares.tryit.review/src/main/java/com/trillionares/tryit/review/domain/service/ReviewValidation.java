package com.trillionares.tryit.review.domain.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewValidation {

    private boolean isAdmin(String role) {
        return "ROLE_ADMIN".equals(role);
    }

    private boolean isMember(String role) {
        return "ROLE_MEMBER".equals(role);
    }

    private boolean isSameAuthorAndModifier(UUID author, UUID modifier) {
        return author.equals(modifier);
    }

    public boolean isNotCreateValidation(String role, Boolean isSelected) {
        return !((isAdmin(role) || isMember(role)) && isSelected);
    }

    public boolean isNotUpdateValidation(String role, UUID author, UUID modifier) {
        return !(isAdmin(role) || isSameAuthorAndModifier(author,modifier));
    }

    public boolean isNotDeleteValidation(String role, UUID author, UUID deleter) {
        return !(isAdmin(role) || isSameAuthorAndModifier(author,deleter));
    }
}