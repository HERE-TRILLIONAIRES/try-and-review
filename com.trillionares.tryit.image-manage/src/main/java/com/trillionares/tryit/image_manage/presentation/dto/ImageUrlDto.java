package com.trillionares.tryit.image_manage.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrlDto {
    private String imageUrl;

    public static ImageUrlDto from(String imageUrl) {
        return ImageUrlDto.builder()
            .imageUrl(imageUrl)
            .build();
    }
}
