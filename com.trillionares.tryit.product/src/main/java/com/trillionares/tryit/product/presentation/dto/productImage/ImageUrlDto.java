package com.trillionares.tryit.product.presentation.dto.productImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrlDto {
    private String imageUrl;

    public void setDefaultImage(String defaultProductImgUrl) {
        this.imageUrl = defaultProductImgUrl;
    }
}
