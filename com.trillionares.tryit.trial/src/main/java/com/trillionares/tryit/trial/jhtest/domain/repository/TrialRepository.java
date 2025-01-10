package com.trillionares.tryit.trial.jhtest.domain.repository;

import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialRepository extends JpaRepository<Trial, UUID> {

    Optional<Trial> findBySubmissionIdIsDeletedFalse(UUID submissionId);
}
