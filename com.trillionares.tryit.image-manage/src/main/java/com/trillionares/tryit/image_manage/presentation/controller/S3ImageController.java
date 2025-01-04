package com.trillionares.tryit.image_manage.presentation.controller;

import com.trillionares.tryit.image_manage.domain.common.message.S3Message;
import com.trillionares.tryit.image_manage.domain.service.S3ImageService;
import com.trillionares.tryit.image_manage.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3ImageController {

    private final S3ImageService s3ImageService;

    @PostMapping("/upload")
    public BaseResponseDto<ImageUrlDto> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        try{
            ImageUrlDto imageUrlDto = s3ImageService.upload(image);
            return BaseResponseDto.from(HttpStatus.OK.value(), HttpStatus.OK, S3Message.UPLOAD_IMAGE_SUCCESS.getMessage(), imageUrlDto);
        } catch (RuntimeException re){
            return BaseResponseDto.from(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, S3Message.NOT_DEFINED_SERVER_RUNTIME_ERROR.getMessage(), null);
        }
    }
}
