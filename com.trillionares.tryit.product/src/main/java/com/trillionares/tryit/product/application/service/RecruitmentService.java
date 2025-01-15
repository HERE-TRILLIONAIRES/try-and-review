package com.trillionares.tryit.product.application.service;

import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;
import com.trillionares.tryit.product.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.presentation.dto.common.kafka.KafkaMessage;
import com.trillionares.tryit.product.presentation.dto.common.kafka.RecruitmentSubmissionResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.CreateRecruitmentRequest;
import com.trillionares.tryit.product.presentation.dto.request.UpdateRecruitmentRequest;
import com.trillionares.tryit.product.presentation.dto.request.UpdateRecruitmentStatusRequest;
import com.trillionares.tryit.product.presentation.dto.response.GetRecruitmentResponse;
import com.trillionares.tryit.product.presentation.dto.response.RecruitmentIdResponse;
import com.trillionares.tryit.product.presentation.dto.response.UpdateRecruitmentStatusResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Transactional
    public RecruitmentIdResponse createRecruitment(CreateRecruitmentRequest request) {
        Recruitment recruitment = Recruitment.builder()
                .productId(request.productId())
                .recruitmentTitle(request.title())
                .recruitmentDescription(request.description())
                .recruitmentStartDate(request.startTime())
                .recruitmentDuration(request.during())
                .recruitmentEndDate(request.endTime())
                .maxParticipants(request.maxParticipants())
                .recruitmentStatus(RecruitmentStatus.WAITING)
                .build();

        recruitmentRepository.save(recruitment);

        return new RecruitmentIdResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentIdResponse updateRecruitment(UUID recruitmentId,
                                                   UpdateRecruitmentRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        recruitment.updateRecruitment(request.title(), request.description(), request.startTime(),
                request.during(), request.endTime(), request.maxParticipants());

        recruitmentRepository.save(recruitment);

        return new RecruitmentIdResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentIdResponse deleteRecruitment(UUID recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        // BaseEntity 구현 후 soft delete 로 변경
        recruitmentRepository.delete(recruitment);

        return new RecruitmentIdResponse(recruitment.getRecruitmentId());
    }

    public GetRecruitmentResponse getRecruitment(UUID recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        return GetRecruitmentResponse.fromEntity(recruitment);
    }

    public Slice<GetRecruitmentResponse> getListRecruitment(Pageable pageable) {
        return recruitmentRepository.getRecruitmentList(pageable);
    }

    public UpdateRecruitmentStatusResponse updateRecruitmentStatus(UUID recruitmentId,
                                                                   UpdateRecruitmentStatusRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException(""));

        recruitment.updateStatus(request.status());

        recruitmentRepository.save(recruitment);

        return new UpdateRecruitmentStatusResponse(recruitment.getRecruitmentId(),
                recruitment.getRecruitmentStatus());
    }


    @Transactional
    public boolean checkAndUpdateRecruitment(UUID recruitmentId, int quantity) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Not Found Recruitment"));

        // 모집 상태 확인
        if (recruitment.getRecruitmentStatus() == RecruitmentStatus.ENDED ||
                recruitment.getRecruitmentStatus() == RecruitmentStatus.PAUSED) {
            return false;
        }

        // 모집 인원 초과 확인
        if (recruitment.getCurrentParticipants() + quantity > recruitment.getMaxParticipants()) {
            return false;
        }

        // 모집 정보 업데이트
        recruitment.updateCurrentParticipants(recruitment.getCurrentParticipants() + quantity);
        recruitmentRepository.save(recruitment);
        return true;
    }

    public void sendSubmissionResponse(String submissionId, String recruitmentId, String userId, String status) {
        try {
            RecruitmentSubmissionResponseDto response = RecruitmentSubmissionResponseDto
                    .of(submissionId, recruitmentId, userId, status);

            String responseJson = JsonUtils.toJson(response);
            KafkaMessage sendMessage = KafkaMessage.of(responseJson);
            String sendMessageJson = JsonUtils.toJson(sendMessage);
            kafkaTemplate.send("updateStatus", sendMessageJson);
        } catch (Exception e) {
            throw new RuntimeException("Submission 로 메시지 생성 실패");
        }
    }

}
