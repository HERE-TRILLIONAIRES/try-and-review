package com.trillionares.tryit.product.domain.model.product;

import com.trillionares.tryit.product.domain.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_product", schema = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID productId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_content", nullable = true)
    private String productContent;
    @Column(name = "product_img_id", nullable = false)
    private UUID productImgId;
    @Column(name = "content_img_id", nullable = true)
    private UUID contentImgId;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public void setProductImgId(UUID productImgId) {
        this.productImgId = productImgId;
    }

    public void setContentImgId(UUID contentImgId) {
        this.contentImgId = contentImgId;
    }
}
