package com.trillionares.tryit.image_manage.presentation.controller;

import com.trillionares.tryit.image_manage.domain.service.ImageService;
import com.trillionares.tryit.image_manage.presentation.dto.BaseResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageIdResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageInfoResquestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping()
    public BaseResponseDto<ImageIdResponseDto> createImage(@RequestBody ImageInfoResquestDto requestDto) {
        ImageIdResponseDto responseDto = imageService.createImage(requestDto);

        return BaseResponseDto.from(HttpStatus.CREATED.value(), HttpStatus.CREATED, "이미지가 성공적으로 등록되었습니다.", responseDto);
    }
}
