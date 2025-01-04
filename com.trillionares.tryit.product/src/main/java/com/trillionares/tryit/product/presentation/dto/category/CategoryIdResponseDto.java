package com.trillionares.tryit.product.presentation.dto.category;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryIdResponseDto {
    private UUID categoryId;

    public static CategoryIdResponseDto from(UUID categoryId) {
        return CategoryIdResponseDto.builder()
            .categoryId(categoryId)
            .build();
    }
}
