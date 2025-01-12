package com.trillionares.tryit.trial.jhtest.domain.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionStatus {
    APPLIED("신청"), //(신청)
    SELECTED("당첨"), //(당첨)
    CANCELED("신청취소"), //(신청취소)
    FAILED("낙첨"), //(낙첨)
    REVIEW_SUBMITTED("리뷰 제출"); //(리뷰 제출)

    private final String message;
}
