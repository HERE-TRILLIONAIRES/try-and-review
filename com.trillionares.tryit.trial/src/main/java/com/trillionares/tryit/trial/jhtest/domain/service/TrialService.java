package com.trillionares.tryit.trial.jhtest.domain.service;

import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.domain.repository.TrialRepository;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.trial.TrialInfoResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrialService {

    private final TrialRepository trialRepository;


    public TrialIdResponseDto createTrial(TrialInfoRequestDto requestDto) {
        // TODO: 권한 체크 (사용자)

        // TODO: UserId토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "신청자";

        // TODO: recruitmentID 존재하는지 검증

        Trial trial = TrialInfoRequestDto.toCreateEntity(requestDto, userId, username);

        trialRepository.save(trial);

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    public TrialInfoResponseDto getTrialById(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdIsDeletedFalse(submissionId).orElse(null);
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

        Trial trial = trialRepository.findBySubmissionIdIsDeletedFalse(submissionId).orElse(null);
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
}
