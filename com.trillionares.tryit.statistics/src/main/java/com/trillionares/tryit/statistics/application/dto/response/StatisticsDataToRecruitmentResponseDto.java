package com.trillionares.tryit.statistics.application.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class StatisticsDataToRecruitmentResponseDto {

    private UUID recruitmentId;
    private LocalDateTime recruitmentStartDate;
    private LocalDateTime recruitmentEndDate;
    private LocalDateTime actualEndDate;
    private Long completionTime;
}
