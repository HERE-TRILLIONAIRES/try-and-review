package com.trillionares.tryit.product.domain.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductMessage {
    CREATED_PRODUCT_SUCCESS("신규 상품 생성을 성공했습니다."),
    SEARCH_PRODUCT_LEST_SUCCESS("상품리스트 조회를 성공했습니다."),
    SEARCH_PRODUCT_SUCESS("상품 조회를 성공했습니다."),
    MODIFIED_PRODUCT_SUCCESS("상품 수정을 성공했습니다."),
    DELETED_PRODUCT_SUCCESS("상품 삭제를 성공했습니다."),
    NOT_DEFINED_SERVER_ERROR("정의되지 않은 서버 에러가 발생했습니다."),
    NOT_DEFINED_SERVER_RUNTIME_ERROR("실행 중 정의되지 않은 서버 에러가 발생했습니다.");

    private final String message;
}
