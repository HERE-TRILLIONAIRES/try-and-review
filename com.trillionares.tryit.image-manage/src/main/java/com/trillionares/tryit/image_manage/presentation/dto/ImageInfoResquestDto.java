package com.trillionares.tryit.image_manage.presentation.dto;

import com.trillionares.tryit.image_manage.domain.model.productImage.ProductImage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfoResquestDto {
    private UUID productId;
    private String productImgUrl;
    private Boolean isMainImg;
    private String username;

    public static ProductImage toCreateEntity(ImageInfoResquestDto requestDto) {
        ProductImage productImage = ProductImage.builder()
            .productId(requestDto.getProductId())
            .productImgUrl(requestDto.getProductImgUrl())
            .isMainImg(requestDto.getIsMainImg())
            .build();

        productImage.setDelete(false);
        productImage.setCreatedBy(requestDto.getUsername());
        productImage.setUpdatedBy(requestDto.getUsername());

        return productImage;
    }
}
