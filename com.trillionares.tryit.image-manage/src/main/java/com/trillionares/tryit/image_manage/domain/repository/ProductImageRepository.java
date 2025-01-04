package com.trillionares.tryit.image_manage.domain.repository;

import com.trillionares.tryit.image_manage.domain.model.productImage.ProductImage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, UUID> {
}
