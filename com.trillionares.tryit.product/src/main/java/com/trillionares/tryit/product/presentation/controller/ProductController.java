package com.trillionares.tryit.product.presentation.controller;

import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.service.ProductService;
import com.trillionares.tryit.product.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoRequestDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

            return BaseResponseDto.from(200, null, "상품 등록 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public BaseResponseDto<List<ProductInfoResponseDto>> getProduct(
            @RequestParam(required = false)List<UUID> idList,
            @QuerydslPredicate(root = Product.class) Predicate predicate,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
            ) {
        try {
            List<ProductInfoResponseDto> responseDto = productService.getProduct(
                    idList, predicate, pageable
            );

            return BaseResponseDto.from(200, null, "상품 조회 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{productId}")
    public BaseResponseDto<ProductInfoResponseDto> getProductById(@PathVariable("productId") UUID productId) {
        try {
            ProductInfoResponseDto responseDto = productService.getProductById(productId);

            return BaseResponseDto.from(200, null, "상품 조회 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{productId}")
    public BaseResponseDto<ProductIdResponseDto> updateProduct(@PathVariable("productId") UUID productId, @RequestBody ProductInfoRequestDto requestDto) {
        try {
            ProductIdResponseDto responseDto = productService.updateProduct(productId, requestDto);

            return BaseResponseDto.from(200, null, "상품 수정 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
