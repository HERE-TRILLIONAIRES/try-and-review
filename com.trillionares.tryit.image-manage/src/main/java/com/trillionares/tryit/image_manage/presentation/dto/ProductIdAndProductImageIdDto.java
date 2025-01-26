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
    private String productImgUrl;

    public static ProductIdAndProductImageIdDto of(UUID productId, UUID imageId, String productImgUrl) {
        return ProductIdAndProductImageIdDto.builder()
            .productId(productId)
            .productImageId(imageId)
            .productImgUrl(productImgUrl)
            .build();
    }
}
