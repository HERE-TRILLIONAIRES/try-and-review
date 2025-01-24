package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.product.ProductItem;
import org.springframework.data.repository.CrudRepository;

public interface ProductItemRepository extends CrudRepository<ProductItem, String> {
}
