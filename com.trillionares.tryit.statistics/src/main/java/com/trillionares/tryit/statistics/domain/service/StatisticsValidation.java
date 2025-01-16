package com.trillionares.tryit.statistics.domain.service;

import org.springframework.stereotype.Service;

@Service
public class StatisticsValidation {

    private boolean isAdmin(String role) {
        return "ROLE_ADMIN".equals(role);
    }

    private boolean isCompany(String role) {
        return "ROLE_COMPANY".equals(role);
    }
}
