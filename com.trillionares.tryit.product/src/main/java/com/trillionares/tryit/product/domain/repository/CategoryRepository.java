package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.category.Category;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByCategoryNameAndIsDeleteFalse(String categoryName);
}
