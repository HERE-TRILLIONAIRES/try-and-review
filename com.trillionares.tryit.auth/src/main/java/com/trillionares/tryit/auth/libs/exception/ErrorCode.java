package com.trillionares.tryit.auth.libs.exception;

public enum ErrorCode {

  // 클라이언트 오류 (4xx)
  INVALID_INPUT_VALUE(400, "입력값이 올바르지 않습니다"),
  INVALID_TYPE_VALUE(400, "타입이 올바르지 않습니다"),
  ENTITY_NOT_FOUND(400, "엔티티를 찾을 수 없습니다"),
  INVALID_PASSWORD(400, "비밀번호가 올바르지 않습니다"),
  FORBIDDEN(403, "접근 권한이 없습니다"),
  UNAUTHORIZED(401, "인증되지 않은 접근입니다"),

  // User 오류
  USER_NOT_FOUND(405, "존재하지 않는 사용자입니다."),
  USER_ALREADY_EXIST(406, "이미 존재하는 사용자입니다."),
  ROLE_NOT_FOUND(407, "존재하지 않는 역할입니다."),
  ID_NOT_FOUND(408, "존재하지 않는 ID입니다."),
  USER_ALREADY_DELETED(409,"이미 탈퇴된 사용자입니다."),

  // 서버 오류 (5xx)
  INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다"),
  DATABASE_ERROR(500, "데이터베이스 오류가 발생했습니다"),
  FILE_PROCESSING_ERROR(500, "파일 처리 중 오류가 발생했습니다"),
  EXTERNAL_SERVICE_ERROR(500, "외부 서비스 연동 중 오류가 발생했습니다"),

  // 데이터 검증 관련
  INVALID_LENGTH(400, "길이가 올바르지 않습니다"),
  INVALID_FORMAT(400, "형식이 올바르지 않습니다"),
  INVALID_RANGE(400, "범위가 올바르지 않습니다"),
  INVALID_DATE(400, "날짜가 올바르지 않습니다");

  private final int status;
  private final String message;

  ErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

}
