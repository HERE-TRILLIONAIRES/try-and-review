package com.trillionares.tryit.statistics.libs.config;
import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateDataResponseDto;
import com.trillionares.tryit.statistics.domain.client.ProductClient;
import com.trillionares.tryit.statistics.domain.client.ReviewClient;
import com.trillionares.tryit.statistics.domain.model.Statistics;
import com.trillionares.tryit.statistics.domain.respository.StatisticsRepository;
import com.trillionares.tryit.statistics.presentation.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticsScheduler {

    private final ReviewClient reviewClient;
    private final ProductClient productClient;
    private final StatisticsRepository statisticsRepository;

    @Scheduled(cron = "00 00 00 * * *")
    @Transactional
    public void createStatisticsSchedule() {

        BaseResponse<List<StatisticsCreateDataResponseDto>>
                statisticsCreateDataResponseDtoBaseResponse = reviewClient.getStatisticsDataToReview();

        statisticsCreateDataResponseDtoBaseResponse.getData()
                .forEach(statisticsDataDto
                        -> statisticsRepository.save(Statistics.of(
                        productClient.getProductInfoStatisticsToProduct(
                                statisticsDataDto.getProductId()).getData().getUserId(),
                        statisticsDataDto.getProductId(),
                        statisticsDataDto.getHighestScore(),
                        statisticsDataDto.getLowestScore(),
                        statisticsDataDto.getAverageScore(),
                        statisticsDataDto.getReviewCount(),
                        productClient.getStatisticsDataToRecruitment(
                                statisticsDataDto.getProductId()).getBody().getCompletionTime())));
    }
}
