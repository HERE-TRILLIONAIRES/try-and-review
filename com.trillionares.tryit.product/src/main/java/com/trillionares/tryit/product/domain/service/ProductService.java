package com.trillionares.tryit.product.domain.service;

import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.repository.ProductRepository;
import com.trillionares.tryit.product.presentation.dto.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductIdResponseDto createProduct(ProductInfoRequestDto requestDto) {
        // TODO: 권한 체크 (관리자, 판매자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        UUID productImgId = UUID.randomUUID();
        UUID contentImgId = UUID.randomUUID();

        Product product = ProductInfoRequestDto.toCreateEntity(requestDto, userId, productImgId, contentImgId);
        productRepository.save(product);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }
}
