package com.trillionares.tryit.product.presentation.dto.common.kafka;

public record SubmissionToRecruitmentRequestDto (
         String submissionId,
         String recruitmentId,
         String userId,
         int quantity,
         String submissionTime,
         String messageId,
         String eventTimestamp
) {
}