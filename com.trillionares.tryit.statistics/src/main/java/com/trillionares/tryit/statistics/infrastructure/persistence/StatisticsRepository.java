package com.trillionares.tryit.statistics.infrastructure.persistence;

import com.trillionares.tryit.statistics.domain.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StatisticsRepository extends JpaRepository<Statistics, UUID> {

    List<Statistics> findAllByUserId(UUID userId);
}
