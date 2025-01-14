package com.trillionares.tryit.trial.jhtest.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusDto {
    private UUID submissionId;
    private String status;
}
