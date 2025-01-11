package com.trillionares.tryit.review.libs.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Checked Exception이 발생했을 때 Global Exception으로 변환하는 클래스
 */
@Slf4j
@Component
public class ExceptionConverter {

    public GlobalException convertToBaseException(Exception e) {
        log.info("Converting checked exception to unchecked", e);

        // 기타 예외 처리
        return new GlobalException(
                ErrorCode.INTERNAL_SERVER_ERROR,
                "알 수 없는 오류가 발생했습니다: " + e.getMessage()
        );
    }
}