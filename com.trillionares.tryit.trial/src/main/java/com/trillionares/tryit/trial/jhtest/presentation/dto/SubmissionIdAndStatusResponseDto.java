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
public class SubmissionIdAndStatusResponseDto {
    private UUID submissionId;
    private Boolean isSelected;

    public static SubmissionIdAndStatusResponseDto of(UUID submissionId, Boolean isSelected) {
        return SubmissionIdAndStatusResponseDto.builder()
                .submissionId(submissionId)
                .isSelected(isSelected)
                .build();
    }
}
