package com.trillionares.tryit.image_manage.presentation.controller;

import com.trillionares.tryit.image_manage.domain.common.message.ImageMessage;
import com.trillionares.tryit.image_manage.domain.service.ImageService;
import com.trillionares.tryit.image_manage.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageIdResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageInfoResquestDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageUrlDto;
import com.trillionares.tryit.image_manage.presentation.exception.ImageNotFoundException;
import com.trillionares.tryit.image_manage.presentation.exception.ImageUrlNotFoundException;
import com.trillionares.tryit.image_manage.presentation.exception.RequestException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping()
    public BaseResponseDto<ImageIdResponseDto> createImage(@RequestBody ImageInfoResquestDto requestDto) {
        try {
            ImageIdResponseDto responseDto = imageService.createImage(requestDto);
            return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED,
                    ImageMessage.CREATED_IMAGE_SUCCESS.getMessage(), responseDto);
        } catch (RequestException reqe) {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                    reqe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @GetMapping("/{product_image_id}")
    public BaseResponseDto<ImageUrlDto> getImageUrlById(@PathVariable("product_image_id") UUID productImgId) {
        try{
            ImageUrlDto responseDto = imageService.getImageUrlById(productImgId);

            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK,
                    ImageMessage.SEARCH_IMAGE_URL_SUCCESS.getMessage(), responseDto);
        } catch (ImageUrlNotFoundException iunfe) {
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                    iunfe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }

    @DeleteMapping("/{product_image_id}")
    public BaseResponseDto<ImageIdResponseDto> deleteImage(
            @PathVariable("product_image_id") UUID productImgId,
            @RequestParam("username") String username
    ) {
        try {
            ImageIdResponseDto responseDto = imageService.deleteImage(productImgId, username);
            return BaseResponseDto.from(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT,
                    ImageMessage.DELETED_IMAGE_SUCCESS.getMessage(), responseDto);
        } catch (ImageNotFoundException infe) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    infe.getMessage(), null);
        } catch (RuntimeException re) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        } catch (Exception e) {
            return BaseResponseDto.from(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,
                    ImageMessage.NOT_DEFINED_SERVER_ERROR.getMessage(), null);
        }
    }
}
