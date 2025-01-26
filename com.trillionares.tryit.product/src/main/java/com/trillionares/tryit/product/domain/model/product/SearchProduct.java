package com.trillionares.tryit.product.domain.model.product;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(indexName = "product")
public class SearchProduct {
    @Id
    private String productId;
    private String userId;
    private String seller;
    private String productName;
    private String productContent;
    private String productImgUrl;
    private String contentImgId;
    private String category;
    private String content;

    public void updateImageInfo(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}
