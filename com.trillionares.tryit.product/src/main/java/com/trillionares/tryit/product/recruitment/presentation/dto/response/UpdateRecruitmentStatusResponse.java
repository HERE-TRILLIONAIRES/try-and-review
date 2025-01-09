package com.trillionares.tryit.product.recruitment.presentation.dto.response;

import com.trillionares.tryit.product.recruitment.domain.model.type.RecruitmentStatus;
import java.util.UUID;

public record UpdateRecruitmentStatusResponse(
        UUID recruitmentId,
        RecruitmentStatus status
) {
}
