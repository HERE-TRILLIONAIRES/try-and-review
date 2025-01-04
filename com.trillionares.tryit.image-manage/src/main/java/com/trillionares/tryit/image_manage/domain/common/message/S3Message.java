package com.trillionares.tryit.image_manage.domain.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum S3Message {
    EMPTY_FILE("파일이 비어있습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("이미지 업로드 중 IO exception이 발생했습니다."),
    NO_FILE_EXTENTION("파일 확장자가 없습니다."),
    INVALID_FILE_EXTENTION("잘못된 파일 확장자입니다."),
    PUT_OBJECT_EXCEPTION("S3 저장에 실패했습니다.");

    private final String message;
}
