package com.trillionares.tryit.product.domain.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.product.domain.model.category.Category;
import com.trillionares.tryit.product.domain.model.category.ProductCategory;
import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.model.product.QProduct;
import com.trillionares.tryit.product.domain.repository.CategoryRepository;
import com.trillionares.tryit.product.domain.repository.ProductCategoryRepository;
import com.trillionares.tryit.product.domain.repository.ProductRepository;
import com.trillionares.tryit.product.presentation.dto.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoRequestDto;
import com.trillionares.tryit.product.presentation.dto.ProductInfoResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    public ProductIdResponseDto createProduct(ProductInfoRequestDto requestDto) {
        // TODO: 권한 체크 (관리자, 판매자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        UUID productImgId = UUID.randomUUID();
        UUID contentImgId = UUID.randomUUID();

        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getProductCategory());
        if(!category.isPresent()) {
            throw new RuntimeException("카테고리가 존재하지 않습니다.");
        }

        Product product = ProductInfoRequestDto.toCreateEntity(requestDto, userId, productImgId, contentImgId);

        ProductCategory productCategory = mappingProductAndCategory(product, category.get());

        productRepository.save(product);
        productCategoryRepository.save(productCategory);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

    private ProductCategory mappingProductAndCategory(Product product, Category category) {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setProductAndCategory(product, category);
        product.setProductCategory(productCategory);

        return productCategory;
    }

    public List<ProductInfoResponseDto> getProduct(
            List<UUID> idList, Predicate predicate, Pageable pageable
    ) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
        if(idList != null && !idList.isEmpty()) {
            booleanBuilder.and(QProduct.product.productId.in(idList));
        }
        booleanBuilder.and(QProduct.product.isDelete.eq(false));
        booleanBuilder.and(QProduct.product.createdAt.gt(QProduct.product.createdAt.min()));
        Page<Product> productList = productRepository.findAll(predicate, pageable);


        List<ProductInfoResponseDto> responseDto = new ArrayList<>();
        for (Product product : productList) {
            // TODO: User Service 호출해서 Seller 정보 받아오기
            String seller = "나판매";

            if(product.getProductCategory().getCategory().getCategoryName() == null) {
                throw new RuntimeException("카테고리가 존재하지 않습니다.");
            }
            String allCategory = product.getProductCategory().getCategory().getCategoryName();

            // TODO: 이미지 정보 받아오기
            String productMainImgDummydummyURL = "https://dummyimage.com/600x400/000/fff";
//            List<String> productSubImgDummydummyURLList = List.of("https://dummyimage.com/600x400/000/fff");
            List<String> contentImgDummydummyURLList = new ArrayList<>();
            contentImgDummydummyURLList.add("https://dummyimage.com/600x400/000/fff");

            responseDto.add(ProductInfoResponseDto.from(product, seller, allCategory, productMainImgDummydummyURL, contentImgDummydummyURLList));
        }

        return responseDto;
    }

    public ProductInfoResponseDto getProductById(UUID productId) {
        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new RuntimeException("상품이 존재하지 않습니다.");
        }

        // TODO: User Service 호출해서 Seller 정보 받아오기
        String seller = "나판매";

        // TODO: 카테고리 정보 여러개면 문자열 붙이기
        if(product.getProductCategory().getCategory().getCategoryName() == null) {
            throw new RuntimeException("카테고리가 존재하지 않습니다.");
        }
        String allCategory = product.getProductCategory().getCategory().getCategoryName();

        // TODO: 이미지 정보 받아오기
        String productMainImgDummydummyURL = "https://dummyimage.com/600x400/000/fff";
//            List<String> productSubImgDummydummyURLList = List.of("https://dummyimage.com/600x400/000/fff");
        List<String> contentImgDummydummyURLList = new ArrayList<>();
        contentImgDummydummyURLList.add("https://dummyimage.com/600x400/000/fff");

        return ProductInfoResponseDto.from(product, seller, allCategory, productMainImgDummydummyURL, contentImgDummydummyURLList);
    }

    @Transactional
    public ProductIdResponseDto updateProduct(UUID productId, ProductInfoRequestDto requestDto) {
        // TODO: 권한 체크 (관리자, 판매자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "너판매";

        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new RuntimeException("상품이 존재하지 않습니다.");
        }

        product = updateProductElement(username, product, requestDto);
        productRepository.save(product);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

    private Product updateProductElement(String username, Product product, ProductInfoRequestDto requestDto) {
        product.setProductName(requestDto.getProductName());
        product.setProductContent(requestDto.getProductContent());

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        UUID productImgId = UUID.randomUUID();
        UUID contentImgId = UUID.randomUUID();
        product.setProductImgId(productImgId);
        product.setContentImgId(contentImgId);

        // TODO: 변경사항 있으면 updatedBy 수정
        product.setUpdatedBy(username);

        return product;
    }

    @Transactional
    public ProductIdResponseDto deleteProduct(UUID productId) {
        // TODO: 권한 체크 (관리자, 판매자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "너판매";

        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new RuntimeException("상품이 존재하지 않습니다.");
        }

        product.delete(username);
        productRepository.save(product);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }
}
