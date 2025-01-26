package com.trillionares.tryit.product.presentation.dto;

import com.thoughtworks.xstream.core.util.PresortedSet;
import com.trillionares.tryit.product.domain.model.product.ProductItem;
import com.trillionares.tryit.product.presentation.dto.response.ProductInfoResponseDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoToProductItemDto {
    private UUID productId;
    private UUID userId;
    private String seller;
    private String productName;
    private String productContent;
    private String productImgUrl;
    private UUID contentImgId;
    private String category;


    public static ProductItem from(ProductInfoResponseDto responseDto) {
        return ProductItem.builder()
                .productId(responseDto.getProductId())
                .userId(responseDto.getUserId())
                .seller(responseDto.getSeller())
                .productName(responseDto.getName())
                .productContent(responseDto.getContent())
                .productImgUrl(responseDto.getMainProductImgUrl())
                .contentImgId(null)
                .category(responseDto.getCategory())
                .build();
    }
}
