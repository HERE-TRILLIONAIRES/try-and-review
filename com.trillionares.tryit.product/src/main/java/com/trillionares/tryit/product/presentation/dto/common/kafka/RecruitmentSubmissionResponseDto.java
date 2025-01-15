package com.trillionares.tryit.product.presentation.dto.common.kafka;

public record RecruitmentSubmissionResponseDto(
        String submissionId,
        String recruitmentId,
        String userId,
        String status
) {
    public static RecruitmentSubmissionResponseDto of(String submissionId, String recruitmentId, String userId,
                                                      String status) {
        return new RecruitmentSubmissionResponseDto(submissionId, recruitmentId, userId, status);
    }
}
