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
public class ImageIdResponseDto {
    private UUID imageId;

    public static ImageIdResponseDto from(UUID imageId) {
        return ImageIdResponseDto.builder()
            .imageId(imageId)
            .build();
    }
}
