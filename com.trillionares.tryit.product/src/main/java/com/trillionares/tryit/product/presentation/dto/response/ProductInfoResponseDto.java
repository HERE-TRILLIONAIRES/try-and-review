package com.trillionares.tryit.product.presentation.dto.response;

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
public class ProductInfoResponseDto {
    private UUID productId;
    private String seller;
    private String name;
    private String content;
    private String category;
    private String mainProductImgUrl;
//    private List<String> subProductImgUrlList;
    private List<String> contentImgUrlList;

    public static ProductInfoResponseDto from(
            Product product, String seller, String allCategory, String dummyURL,
            List<String> contentImgDummydummyURLList) {


        return ProductInfoResponseDto.builder()
            .productId(product.getProductId())
            .seller(seller)
            .name(product.getProductName())
            .content(product.getProductContent())
            .category(allCategory)
            .mainProductImgUrl(dummyURL)
            .contentImgUrlList(contentImgDummydummyURLList)
            .build();
    }
}
