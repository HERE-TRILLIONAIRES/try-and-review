package com.trillionares.tryit.product.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.product.domain.client.ImageClient;
import com.trillionares.tryit.product.domain.common.json.JsonUtils;
import com.trillionares.tryit.product.domain.common.message.CategoryMessage;
import com.trillionares.tryit.product.domain.common.message.ProductMessage;
import com.trillionares.tryit.product.domain.model.category.Category;
import com.trillionares.tryit.product.domain.model.category.ProductCategory;
import com.trillionares.tryit.product.domain.model.product.Product;
import com.trillionares.tryit.product.domain.model.product.QProduct;
import com.trillionares.tryit.product.domain.client.AuthClient;
import com.trillionares.tryit.product.domain.repository.CategoryRepository;
import com.trillionares.tryit.product.domain.repository.ProductCategoryRepository;
import com.trillionares.tryit.product.domain.repository.ProductRepository;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageInfoResquestDto;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageUrlDto;
import com.trillionares.tryit.product.presentation.dto.response.ProductIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.request.ProductInfoRequestDto;
import com.trillionares.tryit.product.presentation.dto.response.ProductInfoResponseDto;
import com.trillionares.tryit.product.presentation.exception.CategoryNotFoundException;
import com.trillionares.tryit.product.presentation.exception.CreateProductMainImageIdException;
import com.trillionares.tryit.product.presentation.exception.CreateProductMainImageUrlException;
import com.trillionares.tryit.product.presentation.exception.ProductMainImageNotFoundException;
import com.trillionares.tryit.product.presentation.exception.ProductNotFoundException;
import feign.codec.Encoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    @Autowired
    private Encoder encoder;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final ImageClient imageClient;
    private final AuthClient authClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private String defaultProductImgUrl = "https://trillionaires-s3.s3.ap-northeast-2.amazonaws.com/empty_product.png";

    @Transactional
    public ProductIdResponseDto createProductUsingkafka(String username, String role, ProductInfoRequestDto requestDto, MultipartFile productMainImage) {
        if(!validatePermission(role)){
            throw new IllegalArgumentException("권한이 없는 사용자입니다.");
        }

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();

        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getProductCategory());
        if(!category.isPresent()) {
            throw new CategoryNotFoundException(CategoryMessage.NOT_FOUND_CATEGORY.getMessage());
        }

        Product product = ProductInfoRequestDto.toCreateEntity(requestDto, userId, username);

        ProductCategory productCategory = mappingProductAndCategory(product, category.get());
        product.setProductCategory(productCategory);

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        product = mappingProductAndProductMainImgUsingKafka(product, productMainImage, username);

        productRepository.save(product);
        productCategoryRepository.save(productCategory);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

    private Product mappingProductAndProductMainImgUsingKafka(Product product, MultipartFile productMainImage, String username) {
        UUID dummyProductImgId = UUID.randomUUID();
        product.setProductImgId(dummyProductImgId);
        productRepository.save(product);

        ImageUrlDto productMainImageUrl = new ImageUrlDto();
        if(!productMainImage.isEmpty() && !Objects.isNull(productMainImage.getOriginalFilename()) && productMainImage != null){
            productMainImageUrl = imageClient.upload(productMainImage).getData();
            if(productMainImageUrl == null) {
                throw new CreateProductMainImageUrlException(ProductMessage.CREATED_PRODUCT_MAIN_IMAGE_URL_FAIL.getMessage());
            }
        } else {
            productMainImageUrl.setDefaultImage(defaultProductImgUrl);
        }

        ImageInfoResquestDto imageInfoResquestDto = ImageInfoResquestDto.from(product.getProductId(), productMainImageUrl.getImageUrl(), true, username);
        // TODO: usertoken에서 이미지 생성한 사람 정보 같이 넘겨주기
        try {
            String imageInfoResquestDtoStr = JsonUtils.toJson(imageInfoResquestDto);
            kafkaTemplate.send("saveImageToDB", "ImageInfo-req", imageInfoResquestDtoStr);
        } catch (Exception e) {
            throw new RuntimeException("직렬화 실패");
        }
        return product;
    }

    @Transactional
    public ProductIdResponseDto createProduct(String username, String role, ProductInfoRequestDto requestDto, MultipartFile productMainImage) {
        if(!validatePermission(role)){
            throw new IllegalArgumentException("권한이 없는 사용자입니다.");
        }

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();

        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getProductCategory());
        if(!category.isPresent()) {
            throw new CategoryNotFoundException(CategoryMessage.NOT_FOUND_CATEGORY.getMessage());
        }

        Product product = ProductInfoRequestDto.toCreateEntity(requestDto, userId, username);

        ProductCategory productCategory = mappingProductAndCategory(product, category.get());
        product.setProductCategory(productCategory);

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        product = mappingProductAndProductMainImg(product, productMainImage, username);
//        UUID contentImgId = UUID.randomUUID();

        // TODO: ----------------------------

        productRepository.save(product);
        productCategoryRepository.save(productCategory);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

    private Boolean validatePermission(String role) {
        if(role.contains("ADMIN")){
            return true;
        } else if(role.contains("COMPANY")){
            return true;
        }
        return false;
    }

    private Product mappingProductAndProductMainImg(Product product, MultipartFile productMainImage, String username) {
        UUID dummyProductImgId = UUID.randomUUID();
        product.setProductImgId(dummyProductImgId);
        productRepository.save(product);

        ImageUrlDto productMainImageUrl = new ImageUrlDto();
        if(!productMainImage.isEmpty() && !Objects.isNull(productMainImage.getOriginalFilename()) && productMainImage != null){
            productMainImageUrl = imageClient.upload(productMainImage).getData();
            if(productMainImageUrl == null) {
                throw new CreateProductMainImageUrlException(ProductMessage.CREATED_PRODUCT_MAIN_IMAGE_URL_FAIL.getMessage());
            }
        } else {
            productMainImageUrl.setDefaultImage(defaultProductImgUrl);
        }

        ImageInfoResquestDto imageInfoResquestDto = ImageInfoResquestDto.from(product.getProductId(), productMainImageUrl.getImageUrl(), true, username);
        // TODO: usertoken에서 이미지 생성한 사람 정보 같이 넘겨주기
        ImageIdResponseDto productMainImageResponseDto = imageClient.createImage(imageInfoResquestDto).getData();
        if(productMainImageResponseDto == null) {
            throw new CreateProductMainImageIdException(ProductMessage.CREATED_PRODUCT_MAIN_IMAGE_ID_FAIL.getMessage());
        }

        product.setProductImgId(productMainImageResponseDto.getImageId());

        if(dummyProductImgId.equals(product.getProductImgId())) {
            throw new ProductMainImageNotFoundException(ProductMessage.NOT_FOUND_PRODUCT_MAIN_IMAGE.getMessage());
        }

        return product;
    }

    private ProductCategory mappingProductAndCategory(Product product, Category category) {
        ProductCategory productCategory = ProductCategory.setProductAndCategory(product, category);

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
            String seller = authClient.getUserInfo(product.getUserId()).getData().getUsername();

            if(product.getProductCategory().getCategory().getCategoryName() == null) {
                throw new CategoryNotFoundException(CategoryMessage.NOT_FOUND_CATEGORY.getMessage());
            }
            String allCategory = product.getProductCategory().getCategory().getCategoryName();

            // TODO: 이미지 정보 받아오기
//            List<String> productSubImgDummydummyURLList = List.of("https://dummyimage.com/600x400/000/fff");
            List<String> contentImgDummydummyURLList = new ArrayList<>();
            contentImgDummydummyURLList.add("https://dummyimage.com/600x400/000/fff");

            ImageUrlDto productMainImgURL = getImageUrlById(product.getProductImgId());

            // TODO: --------------

            responseDto.add(ProductInfoResponseDto.from(product, seller, allCategory, productMainImgURL.getImageUrl(), contentImgDummydummyURLList));
        }

        return responseDto;
    }

    private ImageUrlDto getImageUrlById(UUID productImgId) {
        if(productImgId == null) {
            throw new ProductMainImageNotFoundException(ProductMessage.NOT_FOUND_PRODUCT_MAIN_IMAGE.getMessage());
        }

        ImageUrlDto productMainImgURL = imageClient.getImageUrlById(productImgId).getData();

        if(productMainImgURL == null) {
            throw new ProductMainImageNotFoundException(ProductMessage.NOT_FOUND_PRODUCT_MAIN_IMAGE_URL.getMessage());
        }

        return productMainImgURL;
    }

    public ProductInfoResponseDto getProductById(UUID productId) {
        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException(ProductMessage.NOT_FOUND_PRODUCT.getMessage());
        }

        String seller = authClient.getUserInfo(product.getUserId()).getData().getUsername();

        // TODO: 카테고리 정보 여러개면 문자열 붙이기
        if(product.getProductCategory().getCategory().getCategoryName() == null) {
            throw new CategoryNotFoundException(CategoryMessage.NOT_FOUND_CATEGORY.getMessage());
        }
        String allCategory = product.getProductCategory().getCategory().getCategoryName();

        // TODO: 이미지 정보 받아오기
//        String productMainImgDummydummyURL = "https://dummyimage.com/600x400/000/fff";
//            List<String> productSubImgDummydummyURLList = List.of("https://dummyimage.com/600x400/000/fff");
        List<String> contentImgDummydummyURLList = new ArrayList<>();
        contentImgDummydummyURLList.add("https://dummyimage.com/600x400/000/fff");

        ImageUrlDto productMainImgURL = getImageUrlById(product.getProductImgId());

        return ProductInfoResponseDto.from(product, seller, allCategory, productMainImgURL.getImageUrl(), contentImgDummydummyURLList);
    }

    @Transactional
    public ProductIdResponseDto updateProduct(String username, String role, UUID productId, ProductInfoRequestDto requestDto,
                                              MultipartFile productMainImage) {
        if(!validatePermission(role)){
            throw new IllegalArgumentException("권한이 없는 사용자입니다.");
        }

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();

        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException(ProductMessage.NOT_FOUND_PRODUCT.getMessage());
        }

        if(!isProductOwner(userId, product.getUserId()) && role.contains("COMPANY")) {
            throw new IllegalArgumentException("판매자가 등록한 상품이 아닙니다.");
        }

        updateProductElement(username, product, requestDto, productMainImage);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

    private Boolean isProductOwner(UUID userId1, UUID userId2) {
        if(userId1.equals(userId2)) {
            return true;
        }
        return false;
    }

    private Product updateProductElement(String username, Product product, ProductInfoRequestDto requestDto,
                                         MultipartFile productMainImage) {
        compareProductName(username, product, requestDto);
        compareProductContent(username, product, requestDto);

        // TODO: ProductImgId, ContentImgId aws s3, DB에 저장 후 받아오기
        UUID contentImgId = UUID.randomUUID();
//        product.setProductImgId(productImgId);
//        product.setContentImgId(contentImgId);

        String originProductMainImgURL = getImageUrlById(product.getProductImgId()).getImageUrl();
        if(!compareProductMainImage(originProductMainImgURL, productMainImage.getOriginalFilename())) {
            product = disconnectProductAndProductMainImg(product, username);
            product = mappingProductAndProductMainImg(product, productMainImage, username);
        }

        compareCategory(username, product, requestDto);

        return product;
    }

    private Boolean compareProductMainImage(String originURL, String originalFilename) {
        if(originURL.contains(originalFilename)) {
            return true;
        }
        return false;
    }

    private Product disconnectProductAndProductMainImg(Product product, String username) {
        UUID orginProductImgId = product.getProductImgId();
        ImageIdResponseDto imageIdResponseDto = imageClient.deleteImage(product.getProductImgId(), username).getData();
        if(imageIdResponseDto == null) {
            throw new ProductMainImageNotFoundException(ProductMessage.NOT_FOUND_PRODUCT_MAIN_IMAGE.getMessage());
        }
        if(!orginProductImgId.equals(imageIdResponseDto.getImageId())) {
            throw new ProductMainImageNotFoundException(ProductMessage.DELETED_PRODUCT_MAIN_IMAGE_FAIL.getMessage());
        }

        return product;
    }


    private void compareProductName(String username, Product product, ProductInfoRequestDto requestDto) {
        if(product.getProductName().equals(requestDto.getProductName())) {
            return;
        }
        product.setProductName(requestDto.getProductName());

        product.setUpdatedBy(username);
        productRepository.save(product);
    }

    private void compareProductContent(String username, Product product, ProductInfoRequestDto requestDto) {
        if(product.getProductContent().equals(requestDto.getProductContent())) {
            return;
        }
        product.setProductContent(requestDto.getProductContent());

        product.setUpdatedBy(username);
        productRepository.save(product);
    }

    private void compareCategory(String username, Product product, ProductInfoRequestDto requestDto) {
        Optional<Category> category = categoryRepository.findByCategoryNameAndIsDeleteFalse(requestDto.getProductCategory());
        if(!category.isPresent()) {
            throw new CategoryNotFoundException(CategoryMessage.NOT_FOUND_CATEGORY.getMessage());
        }

        String orginCategory = product.getProductCategory().getCategory().getCategoryName();
        if(orginCategory.equals(category.get().getCategoryName())) {
            return;
        }

        ProductCategory productCategory = product.getProductCategory();
        productCategory.setCategory(category.get());

        productCategoryRepository.save(productCategory);

        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy(username);
        productRepository.save(product);
    }

    @Transactional
    public ProductIdResponseDto deleteProduct(UUID productId) {
        // TODO: 권한 체크 (관리자, 판매자)

        // TODO: UserId 토큰에서 받아오기
        UUID userId = UUID.randomUUID();
        String username = "너판매";

        Product product = productRepository.findByProductIdAndIsDeleteFalse(productId).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException(ProductMessage.NOT_FOUND_PRODUCT.getMessage());
        }

        product = disconnectProductAndProductMainImg(product, username);
        product.delete(username);
        productRepository.save(product);

        ProductIdResponseDto responseDto = ProductIdResponseDto.from(product.getProductId());
        return responseDto;
    }

}
