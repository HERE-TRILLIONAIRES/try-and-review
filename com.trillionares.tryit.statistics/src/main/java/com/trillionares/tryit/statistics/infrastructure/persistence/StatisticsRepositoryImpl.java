package com.trillionares.tryit.statistics.infrastructure.persistence;

import com.trillionares.tryit.statistics.domain.model.Statistics;
import com.trillionares.tryit.statistics.domain.respository.StatisticsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatisticsRepositoryImpl extends JpaRepository<Statistics, UUID>, StatisticsRepository {
}
