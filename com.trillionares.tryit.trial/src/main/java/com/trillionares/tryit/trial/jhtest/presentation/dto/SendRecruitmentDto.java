package com.trillionares.tryit.trial.jhtest.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendRecruitmentDto {
    private UUID submissionId;
    private UUID recruitmentId;
    private UUID userId;
    private Integer quantity;
    private String submissionTime;

    public static SendRecruitmentDto of(UUID submissionId, UUID recruitmentId, UUID userId, Integer quantity, String createdAt) {
        return SendRecruitmentDto.builder()
                .submissionId(submissionId)
                .recruitmentId(recruitmentId)
                .userId(userId)
                .quantity(quantity)
                .submissionTime(createdAt)
                .build();
    }
}
