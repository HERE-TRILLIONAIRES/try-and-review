package com.trillionares.tryit.product.application.service;

import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.repository.ProductRepository;
import com.trillionares.tryit.product.presentation.dto.ProductIdAndProductImageIdDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductRepository productRepository;

    @Transactional
    @KafkaListener(groupId = "productImageManagement", topics = "updateImageIdOfProduct")
    public void updateImageIdOfProduct(String message) throws Exception {
        log.info("updateImageIdOfProduct: {}", message);

        ProductIdAndProductImageIdDto pidAndPimgidDto = JsonUtils.fromJson(message, ProductIdAndProductImageIdDto.class);

        Optional<Product> product = productRepository.findByProductIdAndIsDeleteFalse(pidAndPimgidDto.getProductId());

        if (!product.isPresent() || product == null) {
            throw new Exception("Product Not Found");
        }

        product.get().setProductImgId(pidAndPimgidDto.getProductImageId());

        productRepository.save(product.get());
    }
}
