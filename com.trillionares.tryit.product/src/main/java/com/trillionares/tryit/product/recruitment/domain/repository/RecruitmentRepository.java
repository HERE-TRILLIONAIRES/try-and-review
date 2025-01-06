package com.trillionares.tryit.product.recruitment.domain.repository;

import com.trillionares.tryit.product.recruitment.domain.model.Recruitment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID> {
}
