package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.category.ProductCategory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
