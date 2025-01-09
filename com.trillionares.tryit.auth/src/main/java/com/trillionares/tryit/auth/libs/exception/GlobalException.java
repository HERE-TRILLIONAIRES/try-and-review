package com.trillionares.tryit.auth.libs.exception;

public class GlobalException extends RuntimeException {

  private final ErrorCode errorCode;

  public GlobalException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public GlobalException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
