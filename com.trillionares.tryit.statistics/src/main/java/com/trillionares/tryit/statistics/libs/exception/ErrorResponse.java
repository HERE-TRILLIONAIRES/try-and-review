package com.trillionares.tryit.statistics.libs.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 클라이언트에 반환하는 에러 형식을 정의하는 클래스
 */
@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String message;
    private final String path;
    private final List<FieldError> errors;

    @Builder
    private ErrorResponse(int status, String message, String path, List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    @Getter
    @Builder
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;
    }

    public static ErrorResponse of(ErrorCode errorCode, String path) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .path(path)
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String path, List<FieldError> errors) { // validation에서 에러가 나는 경우
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .path(path)
                .errors(errors)
                .build();
    }
}