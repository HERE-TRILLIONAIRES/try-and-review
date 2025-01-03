package com.trillionares.tryit.product.presentation.dto;

import com.trillionares.tryit.product.domain.model.product.Product;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoRequestDto {
    private String productName;
    private String productContent;
//    private String productCategory;
    private String productImgFilename;
//    private List<String> subImgFilenameList;
    private List<String> contentImgFilenameList;

    public static Product toCreateEntity(ProductInfoRequestDto requestDto, UUID userId, UUID productImgId, UUID contentImgId) {
        Product product = Product.builder()
            .userId(userId)
            .productName(requestDto.getProductName())
            .productContent(requestDto.getProductContent())
            .productImgId(productImgId)
            .contentImgId(contentImgId)
            .build();

        product.setDelete(false);
        product.setCreatedBy("admin");
        product.setUpdatedBy("admin");

        return product;
    }
}
