package com.trillionares.tryit.statistics.presentation.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    private Integer status;
    private HttpStatus code;
    private String message;
    private T data;

    public static <T> BaseResponse of(Integer status, HttpStatus code, String message, T data) {
        return BaseResponse.<T>builder()
                .status(status)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}