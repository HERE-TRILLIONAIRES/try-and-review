package com.trillionares.tryit.product.presentation.dto.productImage;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfoResquestDto {
    private UUID productId;
    private String productImgUrl;
    private Boolean isMainImg;
    private String username;

    public static ImageInfoResquestDto from(UUID productId, String productImgUrl, Boolean isMainImg, String username) {
        return ImageInfoResquestDto.builder()
            .productId(productId)
            .productImgUrl(productImgUrl)
            .isMainImg(isMainImg)
            .username(username)
            .build();
    }
}
