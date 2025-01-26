package com.trillionares.tryit.product.application.service;

import com.trillionares.tryit.product.domain.client.AuthClient;
import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import com.trillionares.tryit.product.domain.model.recruitment.RecruitmentItem;
import com.trillionares.tryit.product.domain.model.recruitment.type.RecruitmentStatus;
import com.trillionares.tryit.product.domain.repository.RecruitmentItemRepository;
import com.trillionares.tryit.product.domain.repository.RecruitmentRepository;
import com.trillionares.tryit.product.infrastructure.service.RedisService;
import com.trillionares.tryit.product.presentation.dto.RecruitmentExistAndStatusDto;
import com.trillionares.tryit.product.presentation.dto.RecruitmentToRecruitmentItemDto;
import com.trillionares.tryit.product.presentation.dto.common.kafka.KafkaMessage;
import com.trillionares.tryit.product.presentation.dto.common.kafka.RecruitmentSubmissionResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.CreateRecruitmentRequest;
import com.trillionares.tryit.product.presentation.dto.request.UpdateRecruitmentRequest;
import com.trillionares.tryit.product.presentation.dto.request.UpdateRecruitmentStatusRequest;
import com.trillionares.tryit.product.presentation.dto.response.GetCompletionTimeResponse;
import com.trillionares.tryit.product.presentation.dto.response.GetRecruitmentResponse;
import com.trillionares.tryit.product.presentation.dto.response.RecruitmentIdResponse;
import com.trillionares.tryit.product.presentation.dto.response.UpdateRecruitmentStatusResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    private final RecruitmentItemRepository recruitmentItemRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RedisService redisService;
    private final AuthClient authClient;
    private final RedissonClient redissonClient;


    @Transactional
    public RecruitmentIdResponse createRecruitment(CreateRecruitmentRequest request, String username, String role) {
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        validatePermission(role);

        Recruitment recruitment = Recruitment.builder()
                .userId(userId)
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

        scheduleRecruitmentStatusChange(recruitment.getRecruitmentId(), recruitment.getRecruitmentStartDate(),
                RecruitmentStatus.STARTED);
        scheduleRecruitmentStatusChange(recruitment.getRecruitmentId(), recruitment.getRecruitmentEndDate(),
                RecruitmentStatus.ENDED);

        recruitmentItemRepository.save(RecruitmentToRecruitmentItemDto.from(recruitment));

        return new RecruitmentIdResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentIdResponse updateRecruitment(UUID recruitmentId,
                                                   UpdateRecruitmentRequest request, String username, String role) {
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        validatePermission(role);

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Not Found Recruitment"));

        validateOwnership(recruitmentId, userId);

        recruitment.updateRecruitment(request.title(), request.description(), request.startTime(),
                request.during(), request.endTime(), request.maxParticipants());

        recruitmentRepository.save(recruitment);

        if(recruitmentItemRepository.existsById(String.valueOf(recruitmentId))){
            recruitmentItemRepository.deleteById(String.valueOf(recruitmentId));

            recruitmentItemRepository.save(RecruitmentToRecruitmentItemDto.from(recruitment));
        }

        return new RecruitmentIdResponse(recruitment.getRecruitmentId());
    }

    @Transactional
    public RecruitmentIdResponse deleteRecruitment(UUID recruitmentId, String username, String role) {
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        validatePermission(role);

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Not Found Recruitment"));

        validateOwnership(recruitmentId, userId);
        // BaseEntity 구현 후 soft delete 로 변경
        recruitmentRepository.delete(recruitment);

        if(recruitmentItemRepository.existsById(String.valueOf(recruitmentId))){
            recruitmentItemRepository.deleteById(String.valueOf(recruitmentId));
        }

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

    @Transactional
    public UpdateRecruitmentStatusResponse updateRecruitmentStatus(UUID recruitmentId,
                                                                   UpdateRecruitmentStatusRequest request,
                                                                   String username,
                                                                   String role) {
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        validatePermission(role);

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Not Found Recruitment"));

        validateOwnership(recruitmentId, userId);
        recruitment.updateStatus(request.status());

        if (request.status() == RecruitmentStatus.ENDED) {
            recruitment.updateActualEndDate(LocalDateTime.now());
            recruitment.updateCompletionTime(recruitment.calculateDurationInMillis());
        }

        recruitmentRepository.save(recruitment);

        if(recruitmentItemRepository.existsById(String.valueOf(recruitmentId))){
            recruitmentItemRepository.deleteById(String.valueOf(recruitmentId));

            recruitmentItemRepository.save(RecruitmentToRecruitmentItemDto.from(recruitment));
        }

        return new UpdateRecruitmentStatusResponse(recruitment.getRecruitmentId(),
                recruitment.getRecruitmentStatus());
    }

    @Transactional
    public boolean checkAndUpdateRecruitment(UUID recruitmentId, int quantity) {
        String lockKey = "lock:recruitment:" + recruitmentId; // 고유한 락 키 생성
        RLock lock = redissonClient.getLock(lockKey);

        try {
            // 락 획득 (10초 대기, 5초 후 락 자동 해제)
            if (lock.tryLock(10, 5, TimeUnit.SECONDS)) {
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
            } else {
                throw new RuntimeException("Unable to acquire lock");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while acquiring lock", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock(); // 락 해제
            }
        }
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

    public RecruitmentExistAndStatusDto isExistRecruitmentById(UUID recruitmentId) {
        String status = "not find";
        if(recruitmentItemRepository.existsById(String.valueOf(recruitmentId))){
            RecruitmentItem recruitmentItem = recruitmentItemRepository.findById(String.valueOf(recruitmentId)).get();

            status = recruitmentItem.getRecruitmentStatus();
        }
        else {
            Optional<Recruitment> recruitment = recruitmentRepository.findByRecruitmentIdAndIsDeletedFalse(recruitmentId);

            if (!recruitment.isPresent() || recruitment.isEmpty() || recruitment == null) {
                return RecruitmentExistAndStatusDto.of(false, "NOT_FOUND");
            }

            status = recruitment.get().getRecruitmentStatus().toString();
        }

        if(status.equals("not find")){
            throw new RuntimeException("상태를 찾을 수 없습니다.");
        }

        // TODO: RecruitmentStatus 수정을 임의로 할 수 없다고 생각해서 매핑만 시켜둠
        switch (status) {
            case "WAITING":
                return RecruitmentExistAndStatusDto.of(true, "WAITING");
            case "STARTED":
                return RecruitmentExistAndStatusDto.of(true, "STARTED");
            case "RESTARTED":
                return RecruitmentExistAndStatusDto.of(true, "RESTARTED");
            case "PAUSED":
                return RecruitmentExistAndStatusDto.of(true, "PAUSED");
            case "ENDED":
                return RecruitmentExistAndStatusDto.of(true, "ENDED");
            default:
                return RecruitmentExistAndStatusDto.of(false, "NOT_FOUND");
        }
    }

    private void scheduleRecruitmentStatusChange(UUID recruitmentId, LocalDateTime triggerTime,
                                                 RecruitmentStatus status) {
        long delaySeconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), triggerTime);

        if (delaySeconds <= 0) {
            updateRecruitmentStatusToRedis(recruitmentId, status);
            return;
        }

        // Redis에 TTL 설정으로 상태 변경 예약
        redisService.setDataExpire(
                "recruitment:status:" + recruitmentId + ":" + status.name(),
                status.name(),
                delaySeconds * 1000L
        );


    }

    public void updateRecruitmentStatusToRedis(UUID recruitmentId, RecruitmentStatus status) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Recruitment not found"));

        recruitment.updateStatus(status);

        if (status == RecruitmentStatus.ENDED) {
            recruitment.updateActualEndDate(recruitment.getRecruitmentEndDate());
            recruitment.updateCompletionTime(recruitment.calculateDurationInMillis());
        }
        recruitmentRepository.save(recruitment);

        log.info("모집 ID {}의 상태가 {}로 업데이트되었습니다.", recruitmentId, status);
    }

    private void validatePermission(String role) {
        if (!(role.contains("ADMIN") || role.contains("COMPANY"))) {
            throw new RuntimeException("권한이 없습니다.");
        }
    }

    private void validateOwnership(UUID recruitmentId, UUID userId) {
        UUID ownerId = recruitmentRepository.findOwnerIdByRecruitmentId(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Recruitment not found."));
        if (!ownerId.equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }
    }

    public GetCompletionTimeResponse getCompletionTime(UUID productId) {
        Recruitment recruitment = recruitmentRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Not Found Recruitment"));

        return GetCompletionTimeResponse.fromEntity(recruitment);

    }


}
