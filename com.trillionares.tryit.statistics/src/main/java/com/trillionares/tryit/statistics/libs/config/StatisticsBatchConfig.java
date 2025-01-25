package com.trillionares.tryit.statistics.libs.config;

import com.trillionares.tryit.statistics.domain.client.ProductClient;
import com.trillionares.tryit.statistics.domain.client.ReviewClient;
import com.trillionares.tryit.statistics.domain.respository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StatisticsBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReviewClient reviewClient;
    private final ProductClient productClient;
    private final StatisticsRepository statisticsRepository;

    @Bean
    public Job statisticsCreateJob() {
        return new JobBuilder("statisticsCreateJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(statisticsCreateStep())
                .build();
    }

    @Bean
    public Step statisticsCreateStep() {
        return new StepBuilder("statisticsCreateStep",jobRepository)
                .tasklet(new StatisticsCreateTasklet(reviewClient,productClient,statisticsRepository),transactionManager)
                .build();
    }
}
