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
public class TrialIdResponseDto {
    private UUID trialId;

    public static TrialIdResponseDto from(UUID trialId) {
        return TrialIdResponseDto.builder()
            .trialId(trialId)
            .build();
    }
}
