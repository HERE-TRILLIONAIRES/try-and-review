package com.trillionares.tryit.product.presentation.dto.response;

import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;
import java.util.UUID;

public record UpdateRecruitmentStatusResponse(
        UUID recruitmentId,
        RecruitmentStatus status
) {
}
