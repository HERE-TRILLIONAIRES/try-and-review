package com.trillionares.tryit.product.presentation.dto.response;

import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.model.product.ProductItem;
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
public class ProductInfoResponseDto {
    private UUID productId;
    private String name;
    private UUID userId;
    private String seller;
    private String content;
    private String category;
    private String mainProductImgUrl;
//    private List<String> subProductImgUrlList;
    private List<String> contentImgUrlList;

    public static ProductInfoResponseDto from(
            Product product, UUID userId, String seller, String allCategory, String dummyURL,
            List<String> contentImgDummydummyURLList) {


        return ProductInfoResponseDto.builder()
            .productId(product.getProductId())
            .userId(userId)
            .seller(seller)
            .name(product.getProductName())
            .content(product.getProductContent())
            .category(allCategory)
            .mainProductImgUrl(dummyURL)
            .contentImgUrlList(contentImgDummydummyURLList)
            .build();
    }

    public static ProductInfoResponseDto ofItem(ProductItem productItem) {
        return ProductInfoResponseDto.builder()
                .productId(productItem.getProductId())
                .userId(productItem.getUserId())
                .seller(productItem.getSeller())
                .name(productItem.getProductName())
                .content(productItem.getProductContent())
                .category(productItem.getCategory())
                .mainProductImgUrl(productItem.getProductImgUrl())
                .contentImgUrlList(null)
                .build();
    }
}
