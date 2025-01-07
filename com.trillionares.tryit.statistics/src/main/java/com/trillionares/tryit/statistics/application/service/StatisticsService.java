package com.trillionares.tryit.statistics.application.service;

import com.trillionares.tryit.statistics.application.dto.request.StatisticsCreateRequestDto;
import com.trillionares.tryit.statistics.application.dto.response.StatisticsCreateResponseDto;
import com.trillionares.tryit.statistics.domain.model.Statistics;
import com.trillionares.tryit.statistics.domain.respository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Transactional
    public StatisticsCreateResponseDto createStatistics(StatisticsCreateRequestDto statisticsCreateRequestDto) {
        Statistics statistics =  statisticsRepository.save(Statistics.of(statisticsCreateRequestDto.productId(),statisticsCreateRequestDto.highestScore(),
                statisticsCreateRequestDto.lowestScore(),statisticsCreateRequestDto.reviewCount(),statisticsCreateRequestDto.durationTime()));
        return StatisticsCreateResponseDto.of(statistics.getStatisticsId(),statistics.getCreatedAt());
    }
}
