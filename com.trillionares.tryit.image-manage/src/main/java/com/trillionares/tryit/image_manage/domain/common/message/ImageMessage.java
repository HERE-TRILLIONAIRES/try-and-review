package com.trillionares.tryit.image_manage.domain.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageMessage {
    REQUEST_NULL_EXCEPTION("잘못된 요청입니다.");

    private final String message;
}
