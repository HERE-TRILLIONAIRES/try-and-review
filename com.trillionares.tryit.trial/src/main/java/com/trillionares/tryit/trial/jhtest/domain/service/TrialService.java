package com.trillionares.tryit.trial.jhtest.domain.service;

import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import com.trillionares.tryit.trial.jhtest.domain.repository.TrialRepository;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
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
}
