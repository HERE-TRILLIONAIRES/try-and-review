package com.trillionares.tryit.product.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillionares.tryit.product.application.service.RecruitmentService;
import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.presentation.dto.common.kafka.SubmissionToRecruitmentRequestDto;
import com.trillionares.tryit.product.presentation.dto.response.GetRecruitmentResponse;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentEndpoint {

    private final RecruitmentService recruitmentService;

    @KafkaListener(groupId = "processRecruitmentCheck", topics = "recruitmentExistenceCheck")
    public Boolean recruitmentExistenceCheck(String message) {
        log.info("recruitmentExistenceCheck: {}", message);

        GetRecruitmentResponse responseDto = recruitmentService.getRecruitment(UUID.fromString(message));

        if (responseDto != null) {
            return true;
        }

        return false;
    }

    @KafkaListener(topics = "checkPossible", groupId = "recruitment-tryit")
    public void handleSubmissionRequest(String message) {
        try {
            SubmissionToRecruitmentRequestDto requestDto = JsonUtils.fromJson(message, SubmissionToRecruitmentRequestDto.class);

            // 모집 가능 여부 확인
            boolean isPossible = recruitmentService.checkAndUpdateRecruitment(
                    UUID.fromString(requestDto.recruitmentId()),
                    requestDto.quantity()
            );

            // 결과 상태 결정
            String status = isPossible ? "APPLIED" : "FAILED_CAPACITY";

            // 결과 전송
            recruitmentService.sendSubmissionResponse(
                    requestDto.submissionId(),
                    requestDto.recruitmentId(),
                    requestDto.userId(),
                    status
            );
        } catch (Exception e) {
            log.error("Failed to process message from 'checkPossible'", e);
        }
    }
}
