package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.product.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
