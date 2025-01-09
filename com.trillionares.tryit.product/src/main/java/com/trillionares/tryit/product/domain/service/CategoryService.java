package com.trillionares.tryit.product.domain.service;

import com.trillionares.tryit.product.domain.model.category.Category;
import com.trillionares.tryit.product.domain.repository.CategoryRepository;
import com.trillionares.tryit.product.presentation.dto.category.CategoryIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.category.CategoryInfoRequestDto;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryIdResponseDto createCategory(CategoryInfoRequestDto requestDto) {
        // TODO: 권한 체크 (관리자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "나 관리자";

        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getCategoryName());
        if (category.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }

        Category newCategory = CategoryInfoRequestDto.toEntity(requestDto, username);
        categoryRepository.save(newCategory);

        CategoryIdResponseDto responseDto = CategoryIdResponseDto.from(newCategory.getCategoryId());
        return responseDto;
    }
}
