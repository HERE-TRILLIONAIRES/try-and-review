package com.trillionares.tryit.product.presentation.controller;

import com.trillionares.tryit.product.application.service.CategoryService;
import com.trillionares.tryit.product.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.product.presentation.dto.response.CategoryIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.CategoryInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public BaseResponseDto<CategoryIdResponseDto> createCategory(@RequestBody CategoryInfoRequestDto requestDto) {
        try {
            CategoryIdResponseDto responseDto = categoryService.createCategory(requestDto);

            return BaseResponseDto.from(200, null, "카테고리 등록 성공", responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
