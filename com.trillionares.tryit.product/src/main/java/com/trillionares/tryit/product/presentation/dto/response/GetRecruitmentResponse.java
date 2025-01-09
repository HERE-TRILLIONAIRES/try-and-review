package com.trillionares.tryit.product.presentation.dto.response;

import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record GetRecruitmentResponse(
        UUID recruitmentId,
        UUID productId,
        RecruitmentStatus status,
        String title,
        String description,
        LocalDateTime startTime,
        long during,
        LocalDateTime endTime
) {

    public static GetRecruitmentResponse fromEntity(Recruitment recruitment) {
        return new GetRecruitmentResponse(
                recruitment.getRecruitmentId(),
                recruitment.getProductId(),
                recruitment.getRecruitmentStatus(),
                recruitment.getRecruitmentTitle(),
                recruitment.getRecruitmentDescription(),
                recruitment.getRecruitmentStartDate(),
                recruitment.getRecruitmentDuration(),
                recruitment.getRecruitmentEndDate()
        );
    }
}