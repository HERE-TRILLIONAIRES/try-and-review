package com.trillionares.tryit.statistics.domain.respository;

import com.trillionares.tryit.statistics.domain.model.Statistics;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository {

    Statistics save(Statistics statistics);

    List<Statistics> findAll();
}
