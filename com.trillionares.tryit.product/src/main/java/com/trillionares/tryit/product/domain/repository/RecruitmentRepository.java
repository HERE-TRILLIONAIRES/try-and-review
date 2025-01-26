package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID>, CustomRecruitmentRepository {
    @Query("SELECT r.userId FROM Recruitment r WHERE r.recruitmentId = :recruitmentId")
    Optional<UUID> findOwnerIdByRecruitmentId(@Param("recruitmentId") UUID recruitmentId);

    Optional<Recruitment> findByProductId(UUID productId);

    @Query("SELECT r.recruitmentId FROM Recruitment r WHERE r.recruitmentId = :recruitmentId")
    Optional<Recruitment> findByRecruitmentIdAndIsDeletedFalse(UUID recruitmentId);
}
