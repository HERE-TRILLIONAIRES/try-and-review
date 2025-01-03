package com.trillionares.tryit.product.presentation.exception;

import com.trillionares.tryit.product.domain.common.message.CategoryMessage;
import com.trillionares.tryit.product.domain.common.message.ProductMessage;
import com.trillionares.tryit.product.presentation.dto.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public BaseResponseDto handleRuntimeException(RuntimeException e) {
        if (e.getMessage().equals(CategoryMessage.NOT_FOUND_CATEGORY.getMessage())) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, CategoryMessage.NOT_FOUND_CATEGORY.getMessage(), null);
        } else {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        }
    }
    @ExceptionHandler(Exception.class)
    public BaseResponseDto handleException(Exception e) {
        if (e.getMessage().equals(CategoryMessage.NOT_FOUND_CATEGORY.getMessage())) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, CategoryMessage.NOT_FOUND_CATEGORY.getMessage(), null);
        } else {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }
}
