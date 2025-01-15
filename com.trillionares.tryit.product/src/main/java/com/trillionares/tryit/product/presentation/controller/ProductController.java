package com.trillionares.tryit.product.presentation.controller;

import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.product.application.service.ProductService;
import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.common.message.ProductMessage;
import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.presentation.dto.common.base.BaseResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.ProductInfoRequestDto;
import com.trillionares.tryit.product.presentation.dto.response.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.response.ProductInfoResponseDto;
import com.trillionares.tryit.product.presentation.exception.CategoryNotFoundException;
import com.trillionares.tryit.product.presentation.exception.ProductMainImageNotFoundException;
import com.trillionares.tryit.product.presentation.exception.ProductNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponseDto<ProductIdResponseDto> createProduct(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @RequestPart("productInfoRequestDto") String requestDto,
            @RequestPart(value = "productMainImage") MultipartFile productMainImage
    ) {
        try {
            ProductInfoRequestDto productInfoRequestDto = JsonUtils.fromJson(requestDto, ProductInfoRequestDto.class);
            ProductIdResponseDto responseDto = productService.createProduct(username, role, productInfoRequestDto, productMainImage);

            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, ProductMessage.CREATED_PRODUCT_SUCCESS.getMessage(), responseDto);
        } catch (CategoryNotFoundException cnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, cnfe.getMessage(), null);
        } catch (RuntimeException re){
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch  (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @PostMapping(value = "/kafka", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponseDto<ProductIdResponseDto> createProductUsingKafka(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @RequestPart("productInfoRequestDto") String requestDto,
            @RequestPart(value = "productMainImage") MultipartFile productMainImage
    ) {
        try {
            ProductInfoRequestDto productInfoRequestDto = JsonUtils.fromJson(requestDto, ProductInfoRequestDto.class);
            ProductIdResponseDto responseDto = productService.createProductUsingkafka(username, role, productInfoRequestDto, productMainImage);

            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, ProductMessage.CREATED_PRODUCT_SUCCESS.getMessage(), responseDto);
        } catch (CategoryNotFoundException cnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, cnfe.getMessage(), null);
        } catch (RuntimeException re){
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch  (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
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

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, ProductMessage.SEARCH_PRODUCT_LEST_SUCCESS.getMessage(), responseDto);
        } catch (CategoryNotFoundException cnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, cnfe.getMessage(), null);
        } catch (ProductMainImageNotFoundException pminfe){
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, pminfe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @GetMapping("/{productId}")
    public BaseResponseDto<ProductInfoResponseDto> getProductById(@PathVariable("productId") UUID productId) {
        try {
            ProductInfoResponseDto responseDto = productService.getProductById(productId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, ProductMessage.SEARCH_PRODUCT_SUCESS.getMessage(), responseDto);
        } catch (CategoryNotFoundException cnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, cnfe.getMessage(), null);
        } catch (ProductNotFoundException pnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, pnfe.getMessage(), null);
        } catch (ProductMainImageNotFoundException pminfe){
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, pminfe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @PutMapping("/{productId}")
    public BaseResponseDto<ProductIdResponseDto> updateProduct(
            @RequestHeader("X-Auth-Username") String username,
            @RequestHeader("X-Auth-Role") String role,
            @PathVariable("productId") UUID productId,
            @RequestPart("productInfoRequestDto") String requestDto,
            @RequestPart(value = "productMainImage") MultipartFile productMainImage
    ) {
        try {
            ProductInfoRequestDto productInfoRequestDto = JsonUtils.fromJson(requestDto, ProductInfoRequestDto.class);
            ProductIdResponseDto responseDto = productService.updateProduct(username, role, productId, productInfoRequestDto, productMainImage);

            return BaseResponseDto.from(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, ProductMessage.MODIFIED_PRODUCT_SUCCESS.getMessage(), responseDto);
        } catch (CategoryNotFoundException cnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, cnfe.getMessage(), null);
        } catch (ProductNotFoundException pnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, pnfe.getMessage(), null);
        } catch (IllegalArgumentException ie) {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ie.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @DeleteMapping("/{productId}")
    public BaseResponseDto<ProductIdResponseDto> deleteProduct(@PathVariable("productId") UUID productId) {
        try {
            ProductIdResponseDto responseDto = productService.deleteProduct(productId);

            return BaseResponseDto.from(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, ProductMessage.DELETED_PRODUCT_SUCCESS.getMessage(), responseDto);
        } catch (ProductNotFoundException pnfe) {
            return BaseResponseDto.from(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, pnfe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ProductMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }
}
