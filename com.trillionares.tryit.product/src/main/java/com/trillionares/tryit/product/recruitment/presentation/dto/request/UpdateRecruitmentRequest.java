package com.trillionares.tryit.product.recruitment.presentation.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateRecruitmentRequest(
        String title,
        String description,

        @Future(message = "시작 시간은 현재 시간 이후여야 합니다.")
        LocalDateTime startTime,

        @Positive(message = "모집 기간은 0보다 커야 합니다.")
        Long during,

        @Future(message = "종료 시간은 현재 시간 이후여야 합니다.")
        LocalDateTime endTime,

        @Positive(message = "모집 인원은 0보다 커야 합니다.")
        Long maxParticipants
) {
}
