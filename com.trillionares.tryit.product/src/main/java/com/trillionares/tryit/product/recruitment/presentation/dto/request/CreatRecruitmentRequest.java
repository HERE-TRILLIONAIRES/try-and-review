package com.trillionares.tryit.product.recruitment.presentation.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreatRecruitmentRequest(
        @NotNull(message = "상품 ID는 필수입니다.")
        UUID productId,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "설명은 필수입니다.")
        String description,

        @NotNull(message = "시작 시간은 필수입니다.")
        @Future(message = "시작 시간은 현재 시간 이후여야 합니다.")
        LocalDateTime startTime,

        @Positive(message = "모집 기간은 0보다 커야 합니다.")
        long during,

        @NotNull(message = "종료 시간은 필수입니다.")
        @Future(message = "종료 시간은 현재 시간 이후여야 합니다.")
        LocalDateTime endTime,

        @Positive(message = "모집 인원은 0보다 커야 합니다.")
        long maxParticipants
) {
}
