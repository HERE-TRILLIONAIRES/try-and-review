package com.trillionares.tryit.auth.libs.exception;

import com.trillionares.tryit.auth.libs.exception.ErrorResponse.FieldError;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(GlobalException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(
      GlobalException e,
      HttpServletRequest request) {
    ErrorCode errorCode = e.getErrorCode();
    log.error("GlobalException occurred : ErrorCode = {} message = {} path = {}",
        errorCode.name(), errorCode.getMessage(), request.getRequestURI());

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ErrorResponse.of(errorCode, request.getRequestURI()));
  }

  /**
   * 유효성 검증에 실패했을때 발생하는 예외를 처리합니다.
   * 모든 필드 오류를 수집해 여러개의 오류가 있는 경우 한번에 반환합니다.
   *
   * @param e Spring Framework에서 발생시킨 유효성 검증 예외
   * @param request 현재 HTTP 요청
   * @return 필드 오류 목록이 포함된 에러 응답
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e,
      HttpServletRequest request) {

    List<FieldError> fieldErrors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> ErrorResponse.FieldError.builder()
            .field(error.getField())
            .value(String.valueOf(error.getRejectedValue()))
            .reason(error.getDefaultMessage())
            .build())
        .toList();

    return ResponseEntity
        .status(ErrorCode.INVALID_INPUT_VALUE.getStatus())
        .body(ErrorResponse.of(
            ErrorCode.INVALID_INPUT_VALUE,
            request.getRequestURI(),
            fieldErrors));
  }

  // 접근 권한 없음
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(
      AccessDeniedException e,
      HttpServletRequest request
  ) {
    log.error("Access denied: {}", e.getMessage());

    return ResponseEntity
        .status(ErrorCode.FORBIDDEN.getStatus())
        .body(ErrorResponse.of(
            ErrorCode.FORBIDDEN,
            request.getRequestURI()));
  }

  // 인증 실패
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(
      AuthenticationException e,
      HttpServletRequest request
  ) {
    log.error("Authentication Error: {}", e.getMessage());

    return ResponseEntity
        .status(ErrorCode.UNAUTHORIZED.getStatus())
        .body(ErrorResponse.of(
            ErrorCode.UNAUTHORIZED,
            request.getRequestURI()));
  }

  // 그외 예외 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception e,
      HttpServletRequest request) {
    log.error("Unexpected Exception occurred", e);

    return ResponseEntity
        .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
        .body(ErrorResponse.of(
            ErrorCode.INTERNAL_SERVER_ERROR,
            request.getRequestURI()));
  }

}
