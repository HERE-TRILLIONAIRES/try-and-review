package com.trillionares.tryit.trial.jhtest.presentation.dto;

import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendNotificationDto {
    private UUID submissionId;
    private UUID userId;
    private UUID recruitmentId;
    private String status;
    private String submissionTime;

    public static SendNotificationDto of(UUID submissionId, UUID userId, UUID recruitmentId, String createdAt) {
        return SendNotificationDto.builder()
                .submissionId(submissionId)
                .userId(userId)
                .recruitmentId(recruitmentId)
                .status(SubmissionStatus.APPLIED.getMessage())
                .submissionTime(createdAt)
                .build();
    }
}
