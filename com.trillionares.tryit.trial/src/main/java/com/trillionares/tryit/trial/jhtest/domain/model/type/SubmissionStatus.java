package com.trillionares.tryit.trial.jhtest.domain.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionStatus {
    APPLIED("APPLIED"), //(신청)
    SELECTED("SELECTED"), //(당첨)
    CANCELED("CANCELED"), //(신청취소)
    FAILED("FAILED"), //(낙첨)
    REVIEW_SUBMITTED("REVIEW_SUBMITTED"); //(리뷰 제출)

    private final String message;
}
