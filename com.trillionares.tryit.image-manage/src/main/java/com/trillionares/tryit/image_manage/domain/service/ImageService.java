package com.trillionares.tryit.image_manage.domain.service;

import com.trillionares.tryit.image_manage.domain.common.message.ImageMessage;
import com.trillionares.tryit.image_manage.domain.model.productImage.ProductImage;
import com.trillionares.tryit.image_manage.domain.repository.ProductImageRepository;
import com.trillionares.tryit.image_manage.presentation.dto.ImageIdResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageInfoResquestDto;
import com.trillionares.tryit.image_manage.presentation.exception.RequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ProductImageRepository productImageRepository;

    @Transactional
    public ImageIdResponseDto createImage(ImageInfoResquestDto requestDto) {
        if(requestDto == null
                || requestDto.getProductId() == null
                || requestDto.getProductImgUrl() == null
        ) {
            throw new RequestException(ImageMessage.REQUEST_NULL_EXCEPTION.getMessage());
        }

        ProductImage productImage = ImageInfoResquestDto.toCreateEntity(requestDto);

        // TODO: isMainImg 체크 -> 같은 productId에 대해 1개만 있어야함.

        productImageRepository.save(productImage);

        ImageIdResponseDto responseDto = ImageIdResponseDto.from(productImage.getProductImageId());
        return responseDto;
    }
}
