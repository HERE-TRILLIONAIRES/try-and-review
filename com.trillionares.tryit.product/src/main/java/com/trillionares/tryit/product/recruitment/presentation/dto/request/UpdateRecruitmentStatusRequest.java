package com.trillionares.tryit.product.recruitment.presentation.dto.request;

import com.trillionares.tryit.product.recruitment.domain.model.type.RecruitmentStatus;

public record UpdateRecruitmentStatusRequest (
        RecruitmentStatus status
){
}
