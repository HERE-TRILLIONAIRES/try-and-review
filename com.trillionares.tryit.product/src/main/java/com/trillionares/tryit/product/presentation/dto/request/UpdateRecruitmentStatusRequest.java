package com.trillionares.tryit.product.presentation.dto.request;

import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;

public record UpdateRecruitmentStatusRequest (
        RecruitmentStatus status
){
}
