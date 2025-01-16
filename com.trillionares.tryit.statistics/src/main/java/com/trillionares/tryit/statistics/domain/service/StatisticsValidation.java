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
        System.out.println("role : "+role);
        System.out.println("company : "+company);
        System.out.println("actor : "+actor);
        System.out.println((isCompany(role) && isSameCompanyAndActor(company,actor)));
        System.out.println(!(isAdmin(role) || (isCompany(role) && isSameCompanyAndActor(company,actor))));
        return !(isAdmin(role) || (isCompany(role) && isSameCompanyAndActor(company,actor)));
    }
}