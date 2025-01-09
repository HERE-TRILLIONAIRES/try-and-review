package com.trillionares.tryit.trial.jhtest.domain.service;

import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.domain.repository.TrialRepository;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.trial.TrialInfoResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrialService {

    private final TrialRepository trialRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public TrialIdResponseDto createTrial(TrialInfoRequestDto requestDto) {
        // TODO: 권한 체크 (사용자)

        // TODO: UserId토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "신청자";
        
        // TODO: 이전 신청내역없는지 검증

        // TODO: recruitmentID 존재하는지 검증
//        if(!checkExistRecruitment(requestDto.getRecruitmentId())) {
//            log.error("해당 모집이 없습니다.");
//            throw new RuntimeException("해당 모집이 없습니다.");
//        }

        Trial trial = TrialInfoRequestDto.toCreateEntity(requestDto, userId, username);

        trialRepository.save(trial);

        // TODO: 재고 빼기, 신청시간 담기, 신청자 정보 담기
//        SendNotificationDto sendDto = SendNotificationDto.of(trial.getSubmissionId(), userId);
        kafkaTemplate.send("minusProduct", "quantity", String.valueOf(trial.getQuantity()));

        // TODO: 알람 보내기 (신청되었다는 알람만, 몇번째인지? 당첨되었는지?는 재고와 모집마감시간 비교후 적용)

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    private Boolean checkExistRecruitment(UUID recruitmentId) {
        kafkaTemplate.send("recruitmentExistenceCheck",
                "recruitmentId", String.valueOf(recruitmentId));

        return true;
    }

    public TrialInfoResponseDto getTrialById(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: User Service 호출해서 신청자 정보 받아오기, 내가 신청한건지 확인
        String trialedUser = "신청자";

        return TrialInfoResponseDto.from(trial);
    }

    @Transactional
    public TrialIdResponseDto changeStatusOfTrial(UUID submissionId, SubmissionStatus status) {
        // TODO: 권한 체크 (사용자)

        // TODO: UserId토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "신청자";

        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: 변경 전 변경 가능 상태 비교
        // 신청 -> 당첨, 신청취소, 낙첨
        // 당첨 -> 리뷰 제출
        if((trial.getSubmissionStatus() == SubmissionStatus.APPLIED && (
                status == SubmissionStatus.SELECTED
                        || status == SubmissionStatus.CANCELED
                        || status == SubmissionStatus.FAILED))
        || (trial.getSubmissionStatus() == SubmissionStatus.SELECTED && status == SubmissionStatus.REVIEW_SUBMITTED)) {
            trial.setSubmissionStatus(status);
            trial.setUpdatedBy(username);
            trialRepository.save(trial);
        }

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    @Transactional
    public TrialIdResponseDto deleteTrial(UUID submissionId) {
        // TODO: 권한 체크 (사용자) 본인인지 확인

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "나신청";

        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        trial = toDeletedStatusOfTrial(trial, SubmissionStatus.CANCELED, username);

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    private Trial toDeletedStatusOfTrial(Trial trial, SubmissionStatus submissionStatus, String username) {
        if(trial.getSubmissionStatus() == SubmissionStatus.APPLIED) {
            trial.setSubmissionStatus(submissionStatus);
            trial.delete(username);
            trialRepository.save(trial);
        }

        return trial;
    }
}
