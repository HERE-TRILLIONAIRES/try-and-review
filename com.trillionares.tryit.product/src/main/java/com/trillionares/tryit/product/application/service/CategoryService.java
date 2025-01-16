package com.trillionares.tryit.product.application.service;

import com.trillionares.tryit.product.domain.model.category.Category;
import com.trillionares.tryit.product.domain.repository.CategoryRepository;
import com.trillionares.tryit.product.presentation.dto.response.CategoryIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.CategoryInfoRequestDto;
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
    public CategoryIdResponseDto createCategory(String username, String role, CategoryInfoRequestDto requestDto) {
        if(!validatePermission(role)){
            throw new IllegalArgumentException("권한이 없는 사용자입니다.");
        }

        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getCategoryName());
        if (category.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }

        Category newCategory = CategoryInfoRequestDto.toEntity(requestDto, username);
        categoryRepository.save(newCategory);

        CategoryIdResponseDto responseDto = CategoryIdResponseDto.from(newCategory.getCategoryId());
        return responseDto;
    }

    private Boolean validatePermission(String role) {
        if(role.contains("ADMIN")){
            return true;
        }
        return false;
    }
}
