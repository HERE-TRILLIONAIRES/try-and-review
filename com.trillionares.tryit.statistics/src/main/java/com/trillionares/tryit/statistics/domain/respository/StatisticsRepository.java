package com.trillionares.tryit.statistics.domain.respository;

import com.trillionares.tryit.statistics.domain.model.Statistics;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository {

    Statistics save(Statistics statistics);
}
