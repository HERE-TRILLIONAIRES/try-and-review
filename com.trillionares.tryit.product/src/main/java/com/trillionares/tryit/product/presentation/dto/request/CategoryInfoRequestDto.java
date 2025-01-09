package com.trillionares.tryit.product.presentation.dto.request;

import com.trillionares.tryit.product.domain.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfoRequestDto {
    private String categoryName;

    public static Category toEntity(CategoryInfoRequestDto requestDto, String username) {
        Category category = Category.builder()
            .categoryName(requestDto.getCategoryName())
            .build();

        category.setDelete(false);
        category.setCreatedBy(username);
        category.setUpdatedBy(username);

        return category;
    }
}
