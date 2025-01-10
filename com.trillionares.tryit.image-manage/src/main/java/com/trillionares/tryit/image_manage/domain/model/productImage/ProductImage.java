package com.trillionares.tryit.image_manage.domain.model.productImage;

import static java.time.LocalDateTime.now;

import com.trillionares.tryit.image_manage.domain.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "p_product_image", schema = "product")
public class ProductImage extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "product_image_id", updatable = false, nullable = false)
    private UUID productImageId;
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "product_img_url", nullable = false)
    private String productImgUrl;
    @Column(name = "is_main_img", nullable = false)
    private Boolean isMainImg;

    public void delete(String username) {
        this.setDelete(true);
        this.setDeletedAt(LocalDateTime.now());
        this.setDeletedBy(username);
    }
}
