package com.trillionares.tryit.product.presentation.controller;

import com.trillionares.tryit.product.domain.service.ProductService;
import com.trillionares.tryit.product.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public BaseResponseDto<ProductIdResponseDto> createProduct(@RequestBody ProductInfoRequestDto requestDto) {
        try {
            ProductIdResponseDto responseDto = productService.createProduct(requestDto);

            return BaseResponseDto.froM(200, null, "상품 등록 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
