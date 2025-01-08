package com.trillionares.tryit.product.recruitment.presentation.dto.response;

import com.trillionares.tryit.product.recruitment.domain.model.Recruitment;
import com.trillionares.tryit.product.recruitment.domain.model.type.RecruitmentStatus;
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