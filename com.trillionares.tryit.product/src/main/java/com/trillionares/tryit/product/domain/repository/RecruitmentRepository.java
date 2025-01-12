package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID>, CustomRecruitmentRepository{
}
