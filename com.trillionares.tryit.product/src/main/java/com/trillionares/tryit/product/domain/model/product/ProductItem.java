package com.trillionares.tryit.product.domain.model.product;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("productItem")
public class ProductItem implements Serializable {
    @Id
    private UUID productId;
    private UUID userId;
    private String seller;
    private String productName;
    private String productContent;
    private String productImgUrl;
    private UUID contentImgId;
    private String category;
}
