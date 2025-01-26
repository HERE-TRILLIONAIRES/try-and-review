package com.trillionares.tryit.product.application.service;

import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;
import com.trillionares.tryit.product.infrastructure.service.RedisService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RecruitmentStatusPoller {

    private final RedisService redisService;
    private final RecruitmentService recruitmentService;

    @Scheduled(fixedRate = 30000) // 30초마다 실행
    public void pollForExpiredRecruitmentStatus() {

        List<String> keys = redisService.getKeysByPattern("recruitment:status:*");
        for (String key : keys) {
            String[] keyParts = key.split(":");
            if (keyParts.length != 4) {
                log.warn("잘못된 KEY 형식입니다. : {}", key);
                continue;
            }

            UUID recruitmentId = UUID.fromString(keyParts[2]);
            RecruitmentStatus status = RecruitmentStatus.valueOf(keyParts[3]);

            if (redisService.getTTL(key) <= 0) { // TTL 이 0이거나 만료된 경우
                recruitmentService.updateRecruitmentStatusToRedis(recruitmentId, status);
                redisService.deleteData(key);
                log.info("처리 만료된 KEY 입니다. : {}", key);
            }
        }
    }
}
