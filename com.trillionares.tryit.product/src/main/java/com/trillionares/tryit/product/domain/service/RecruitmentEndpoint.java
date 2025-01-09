package com.trillionares.tryit.product.domain.service;

import com.trillionares.tryit.product.recruitment.application.service.RecruitmentService;
import com.trillionares.tryit.product.recruitment.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.recruitment.presentation.dto.response.GetRecruitmentResponse;
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

    @KafkaListener(groupId = "inventoryManagement", topics = "minusProduct")
    public void minusProduct(String message) {
        log.info("minusProduct: {}", message);

        // TODO: 구매수량 넘겨주면, 재고에서 수량만큼 빼고, 현재 인원 늘려주기
    }
}
