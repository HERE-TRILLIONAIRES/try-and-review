package com.trillionares.tryit.product.presentation.dto.response;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdResponseDto {
    private UUID productId;

    public static ProductIdResponseDto from(UUID productId) {
        return ProductIdResponseDto.builder()
            .productId(productId)
            .build();
    }
}
