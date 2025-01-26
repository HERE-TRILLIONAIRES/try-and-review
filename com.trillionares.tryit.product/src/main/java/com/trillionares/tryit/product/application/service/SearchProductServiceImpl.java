package com.trillionares.tryit.product.application.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.trillionares.tryit.product.domain.model.product.SearchProduct;
import com.trillionares.tryit.product.presentation.dto.response.ProductInfoResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SearchProductServiceImpl {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchProductServiceImpl(ElasticsearchOperations elasticsearchOperations){
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<ProductInfoResponseDto> search(String productName, String productId, String userId, String seller,
                                               String productContent, String category) {
        NativeQueryBuilder query = NativeQuery.builder();
        BoolQuery.Builder boolQuery = QueryBuilders.bool();

        // 1. productName 문자열 포함 조건
        if (productName != null) {
            boolQuery.must(QueryBuilders.queryString(field ->
                    field.fields("productName").query("*%s*".formatted(productName))));
        }

        if (productId != null) {
            boolQuery.filter(QueryBuilders.term(t -> t.field("productId").value(userId)));
        }

        // 2. userId 조건 (정확히 일치)
        if (userId != null) {
            boolQuery.filter(QueryBuilders.term(t -> t.field("userId").value(userId)));
        }

        // 3. seller 조건 (정확히 일치)
        if (seller != null && !seller.isEmpty()) {
            boolQuery.filter(QueryBuilders.term(t -> t.field("seller").value(seller)));
        }

        // 4. productContent 조건 (포함 여부)
        if (productContent != null && !productContent.isEmpty()) {
            boolQuery.must(QueryBuilders.match(m -> m.field("productContent").query(productContent)));
        }

        // 5. category 조건 (정확히 일치)
        if (category != null && !category.isEmpty()) {
            boolQuery.filter(QueryBuilders.term(t -> t.field("category").value(category)));
        }

        query.withQuery(boolQuery.build()._toQuery());
        SearchHits<SearchProduct> hits = elasticsearchOperations.search(query.build(), SearchProduct.class);
        log.info("get products productName: {}, userId: {}, seller: {}, productContent: {}, category: {}",
                productName, userId, seller, productContent, category);
        return hits.map(hit -> toDto(hit.getContent())).toList();
    }

    private static ProductInfoResponseDto toDto(SearchProduct product){
        return ProductInfoResponseDto.builder()
                .productId(UUID.fromString(product.getProductId()))
                .name(product.getProductName())
                .userId(UUID.fromString(product.getUserId()))
                .seller(product.getSeller())
                .content(product.getContent())
                .category(product.getCategory())
                .mainProductImgUrl(product.getProductImgUrl())
                .contentImgUrlList(null)
                .build();

    }
}
