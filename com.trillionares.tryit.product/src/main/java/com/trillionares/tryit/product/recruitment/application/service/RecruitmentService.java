package com.trillionares.tryit.product.recruitment.application.service;

import com.trillionares.tryit.product.recruitment.domain.model.Recruitment;
import com.trillionares.tryit.product.recruitment.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.recruitment.presentation.dto.request.CreatRecruitmentRequest;
import com.trillionares.tryit.product.recruitment.presentation.dto.response.CreatRecruitmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;


    @Transactional
    public CreatRecruitmentResponse createRecruitment(CreatRecruitmentRequest request) {
        Recruitment recruitment = Recruitment.builder()
                .recruitmentTitle(request.title())
                .recruitmentDescription(request.description())
                .recruitmentStartDate(request.startTime())
                .recruitmentDuration(request.during())
                .recruitmentEndDate(request.endTime())
                .maxParticipants(request.maxParticipants())
                .build();

        recruitmentRepository.save(recruitment);
        return new CreatRecruitmentResponse(recruitment.getRecruitmentId());
    }
}
