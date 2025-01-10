package com.trillionares.tryit.product.presentation.dto.request;

import com.trillionares.tryit.product.domain.model.product.Product;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoRequestDto {
    private String productName;
    private String productContent;
    private String productCategory;

    public static Product toCreateEntity(ProductInfoRequestDto requestDto, UUID userId, String username) {
        Product product = Product.builder()
            .userId(userId)
            .productName(requestDto.getProductName())
            .productContent(requestDto.getProductContent())
            .build();

        product.setDelete(false);
        product.setCreatedBy(username);
        product.setUpdatedBy(username);

        return product;
    }
}
