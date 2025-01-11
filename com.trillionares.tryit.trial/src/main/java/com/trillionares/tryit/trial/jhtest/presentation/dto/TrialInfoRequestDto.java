package com.trillionares.tryit.trial.jhtest.presentation.dto;

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
public class TrialInfoRequestDto {
    private UUID recruitmentId;
    private String deliveryAddress;

    public static Trial toCreateEntity(TrialInfoRequestDto requestDto, UUID userId, String username) {
        Trial trial = Trial.builder()
                .recruitmentId(requestDto.getRecruitmentId())
                .userId(userId)
                .submissionStatus(SubmissionStatus.APPLIED)
                .quantity(1)
                .address(requestDto.getDeliveryAddress())
                .build();

        trial.setDeleted(false);
        trial.setCreatedBy(username);
        trial.setUpdatedBy(username);

        return trial;
    }
}
