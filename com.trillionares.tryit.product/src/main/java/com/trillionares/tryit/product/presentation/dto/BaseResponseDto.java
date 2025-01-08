package com.trillionares.tryit.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto<T> {
    private Integer status;
    private HttpStatus code;
    private String message;
    private T data;

    public static <T> BaseResponseDto from(Integer status, HttpStatus code, String message, T data) {
        return BaseResponseDto.<T>builder()
            .status(status)
            .code(code)
            .message(message)
            .data(data)
            .build();
    }
}
