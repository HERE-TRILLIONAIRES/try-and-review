package com.trillionares.tryit.product.domain.client;

import com.trillionares.tryit.product.infrastructure.config.FeignConfig;
import com.trillionares.tryit.product.presentation.dto.common.base.BaseResponseDto;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageIdResponseDto;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageInfoResquestDto;
import com.trillionares.tryit.product.presentation.dto.productImage.ImageUrlDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="image-manage-service", configuration = FeignConfig.class)
public interface ImageClient {

    @PostMapping(value = "/api/s3/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    BaseResponseDto<ImageUrlDto> upload(@RequestPart(value = "productImage", required = false) MultipartFile productImage);

    @PostMapping("/api/images")
    BaseResponseDto<ImageIdResponseDto> createImage(ImageInfoResquestDto requestDto);

    @GetMapping("/api/images/{product_image_id}")
    BaseResponseDto<ImageUrlDto> getImageUrlById(@PathVariable("product_image_id") UUID productImgId);
}
