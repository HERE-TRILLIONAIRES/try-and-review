package com.trillionares.tryit.statistics.libs.config;

import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateDataResponseDto;
import com.trillionares.tryit.statistics.domain.client.ProductClient;
import com.trillionares.tryit.statistics.domain.client.ReviewClient;
import com.trillionares.tryit.statistics.domain.model.Statistics;
import com.trillionares.tryit.statistics.infrastructure.persistence.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

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
                .start(statisticsCreateChunkBasedStep())
                .build();
    }

    @Bean
    public Step statisticsCreateTaskletBasedStep() {
        return new StepBuilder("statisticsCreateTaskletBasedStep",jobRepository)
                .tasklet(new StatisticsCreateTasklet(reviewClient,productClient,statisticsRepository),transactionManager)
                .build();
    }

    @Bean
    public Step statisticsCreateChunkBasedStep() {
        return new StepBuilder("statisticsCreateChunkBasedStep", jobRepository)
                .<StatisticsCreateReviewDataDto, Statistics>chunk(100, transactionManager)
                .reader(statisticsCreateDataReader())
                .processor(statisticsCreateDateProcessor())
                .writer(statisticsCreateDateWriter())
                .build();
    }

    @Bean
    public ItemReader<StatisticsCreateReviewDataDto> statisticsCreateDataReader() {
        List<StatisticsCreateDataResponseDto> statisticsCreateReviewDataDtos
                = reviewClient.getStatisticsDataToReview().getData();

        List<StatisticsCreateReviewDataDto> processedData = statisticsCreateReviewDataDtos.stream()
                .map(createData -> new StatisticsCreateReviewDataDto(
                        createData.getProductId(),
                        createData.getHighestScore(),
                        createData.getLowestScore(),
                        createData.getAverageScore(),
                        createData.getReviewCount()))
                .collect(Collectors.toList());

        return new ListItemReader<>(processedData);
    }

    @Bean
    public ItemProcessor<StatisticsCreateReviewDataDto,Statistics> statisticsCreateDateProcessor() {

        return CreateDataDto -> Statistics.of(
                productClient.getProductInfoStatisticsToProduct(
                        CreateDataDto.productId()).getData().getUserId(),
                CreateDataDto.productId(),
                CreateDataDto.highestScore(),
                CreateDataDto.lowestScore(),
                CreateDataDto.averageScore(),
                CreateDataDto.reviewCount(),
                productClient.getStatisticsDataToRecruitment(
                        CreateDataDto.productId()).getBody().getCompletionTime()
        );
    }

    @Bean
    public ItemWriter<Statistics> statisticsCreateDateWriter() {
        return statisticsRepository::saveAll;
    }
}