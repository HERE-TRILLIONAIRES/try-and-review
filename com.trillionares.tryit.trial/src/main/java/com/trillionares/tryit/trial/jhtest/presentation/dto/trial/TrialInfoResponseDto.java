package com.trillionares.tryit.trial.jhtest.presentation.dto.trial;

import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrialInfoResponseDto {
    private UUID submissionId;
    private UUID recruitmentId;
    private UUID userId;
    private SubmissionStatus submissionStatus;
    private Integer quantity;
    private String address;
    private Long selectedSequence;

    public static TrialInfoResponseDto from(Trial trial) {
        return TrialInfoResponseDto.builder()
                .submissionId(trial.getSubmissionId())
                .recruitmentId(trial.getRecruitmentId())
                .userId(trial.getUserId())
                .submissionStatus(trial.getSubmissionStatus())
                .quantity(trial.getQuantity())
                .address(trial.getAddress())
                .selectedSequence(trial.getSelectedSequence())
                .build();
    }
}
