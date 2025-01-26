package com.trillionares.tryit.statistics.libs.config;

import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateDataResponseDto;
import com.trillionares.tryit.statistics.domain.client.ProductClient;
import com.trillionares.tryit.statistics.domain.client.ReviewClient;
import com.trillionares.tryit.statistics.domain.model.Statistics;
import com.trillionares.tryit.statistics.infrastructure.persistence.StatisticsRepository;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatisticsCreateTasklet implements Tasklet {

    private final ReviewClient reviewClient;
    private final ProductClient productClient;
    private final StatisticsRepository statisticsRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        BaseResponse<List<StatisticsCreateDataResponseDto>> createDataDtos = reviewClient.getStatisticsDataToReview();

        List<Statistics> statisticsList = createDataDtos.getData().stream()
                .map(statisticsDataDto -> Statistics.of(
                        productClient.getProductInfoStatisticsToProduct(
                                statisticsDataDto.getProductId()).getData().getUserId(),
                        statisticsDataDto.getProductId(),
                        statisticsDataDto.getHighestScore(),
                        statisticsDataDto.getLowestScore(),
                        statisticsDataDto.getAverageScore(),
                        statisticsDataDto.getReviewCount(),
                        productClient.getStatisticsDataToRecruitment(
                                statisticsDataDto.getProductId()).getBody().getCompletionTime()
                ))
                .collect(Collectors.toList());

        statisticsRepository.saveAll(statisticsList);

        return RepeatStatus.FINISHED;
    }
}
