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

    private boolean isSameAuthorAndActor(UUID author, UUID actor) {
        return author.equals(actor);
    }

    public boolean isNotCreateValidation(String role, Boolean isSelected) {
        return !((isAdmin(role) || isMember(role)) && isSelected);
    }

    public boolean isNotUpdateValidation(String role, UUID author, UUID modifier) {
        return !(isAdmin(role) || isSameAuthorAndActor(author,modifier));
    }

    public boolean isNotDeleteValidation(String role, UUID author, UUID deleter) {
        return !(isAdmin(role) || isSameAuthorAndActor(author,deleter));
    }
}