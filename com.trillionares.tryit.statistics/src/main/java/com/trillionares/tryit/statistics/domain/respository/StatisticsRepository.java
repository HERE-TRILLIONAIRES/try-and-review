package com.trillionares.tryit.statistics.domain.respository;

import com.trillionares.tryit.statistics.domain.model.Statistics;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository {

    Statistics save(Statistics statistics);

    List<Statistics> findAll();

    List<Statistics> findAllByUserId(UUID userId);
}
