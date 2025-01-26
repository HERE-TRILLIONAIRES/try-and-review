package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.product.SearchProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<SearchProduct, String> {
}
