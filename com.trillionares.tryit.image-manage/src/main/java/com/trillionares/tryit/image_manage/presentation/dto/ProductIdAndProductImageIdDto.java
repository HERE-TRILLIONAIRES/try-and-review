package com.trillionares.tryit.image_manage.presentation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdAndProductImageIdDto {
    private UUID productId;
    private UUID productImageId;

    public static ProductIdAndProductImageIdDto of(UUID productId, UUID imageId) {
        return ProductIdAndProductImageIdDto.builder()
            .productId(productId)
            .productImageId(imageId)
            .build();
    }
}
