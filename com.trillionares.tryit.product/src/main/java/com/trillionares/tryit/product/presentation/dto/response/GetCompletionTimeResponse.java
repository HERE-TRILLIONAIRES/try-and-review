package com.trillionares.tryit.product.presentation.dto.response;

import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.cglib.core.Local;

public record GetCompletionTimeResponse(
        UUID recruitmentId,
        LocalDateTime recruitmentStartDate,
        LocalDateTime recruitmentEndDate,
        LocalDateTime actualEndDate,
        long completionTime
) {
    public static GetCompletionTimeResponse fromEntity(Recruitment recruitment) {
        return new GetCompletionTimeResponse(
                recruitment.getRecruitmentId(),
                recruitment.getRecruitmentStartDate(),
                recruitment.getRecruitmentEndDate(),
                recruitment.getActualEndDate(),
                recruitment.getCompletionTime()
        );
    }
}
