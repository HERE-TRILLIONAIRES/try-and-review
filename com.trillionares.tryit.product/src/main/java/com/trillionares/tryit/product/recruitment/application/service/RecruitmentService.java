package com.trillionares.tryit.product.recruitment.application.service;

import com.trillionares.tryit.product.recruitment.domain.model.Recruitment;
import com.trillionares.tryit.product.recruitment.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.recruitment.presentation.dto.request.CreateRecruitmentRequest;
import com.trillionares.tryit.product.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import com.trillionares.tryit.product.recruitment.presentation.dto.response.RecruitmentResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;


    @Transactional
    public RecruitmentResponse createRecruitment(CreateRecruitmentRequest request) {
        Recruitment recruitment = Recruitment.builder()
                .recruitmentTitle(request.title())
                .recruitmentDescription(request.description())
                .recruitmentStartDate(request.startTime())
                .recruitmentDuration(request.during())
                .recruitmentEndDate(request.endTime())
                .maxParticipants(request.maxParticipants())
                .build();

        recruitmentRepository.save(recruitment);

        return new RecruitmentResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentResponse updateRecruitment(UUID recruitmentId,
                                                 UpdateRecruitmentRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        recruitment.updateRecruitment(request.title(), request.description(), request.startTime(),
                request.during(), request.endTime(), request.maxParticipants());

        recruitmentRepository.save(recruitment);

        return new RecruitmentResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentResponse deleteRecruitment(UUID recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        // BaseEntity 구현 후 soft delete 로 변경
        recruitmentRepository.delete(recruitment);

        return new RecruitmentResponse(recruitment.getRecruitmentId());
    }

}
