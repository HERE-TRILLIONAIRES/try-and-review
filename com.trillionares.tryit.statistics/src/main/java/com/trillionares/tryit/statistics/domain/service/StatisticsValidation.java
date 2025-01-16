package com.trillionares.tryit.statistics.domain.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatisticsValidation {

    private boolean isAdmin(String role) {
        return "ROLE_ADMIN".equals(role);
    }

    private boolean isCompany(String role) {
        return "ROLE_COMPANY".equals(role);
    }

    private boolean isSameCompanyAndActor(UUID company, UUID actor) {
        return company.equals(actor);
    }

    public boolean isNotGetAllValidation(String role) {
        return !isAdmin(role);
    }

    public boolean isNotGetValidation(String role, UUID company, UUID actor) {
        return !(isAdmin(role) || (isCompany(role) && isSameCompanyAndActor(company,actor)));
    }
}