package com.trillionares.tryit.product.domain.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryMessage {
    NOT_FOUND_CATEGORY("카테고리가 존재하지 않습니다.");

    private final String message;
}
