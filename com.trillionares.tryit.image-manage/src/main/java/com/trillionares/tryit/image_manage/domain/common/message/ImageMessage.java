package com.trillionares.tryit.image_manage.domain.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageMessage {
    REQUEST_NULL_EXCEPTION("잘못된 요청입니다."),
    NOT_FOUND_IMAGE_URL("해당 이미지 URL를 찾을 수 없습니다."),
    NOT_DEFINED_SERVER_ERROR("정의되지 않은 서버에러가 발생했습니다."),
    NOT_DEFINED_SERVER_RUNTIME_ERROR("실행 중 정의되지 않은 서버에러가 발생했습니다."),
    SEARCH_IMAGE_URL_SUCCESS("이미지 URL 조회를 성공했습니다."),
    CREATED_IMAGE_SUCCESS("이미지가 성공적으로 등록되었습니다."),
    DELETED_IMAGE_SUCCESS("이미지가 성공적으로 삭제되었습니다."),
    NOT_FOUND_IMAGE("해당 이미지를 찾을 수 없습니다.");

    private final String message;
}
