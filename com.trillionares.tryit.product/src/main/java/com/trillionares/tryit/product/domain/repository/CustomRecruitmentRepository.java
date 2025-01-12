package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.presentation.dto.response.GetRecruitmentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomRecruitmentRepository {
    Slice<GetRecruitmentResponse> getRecruitmentList(Pageable pageable);
}
