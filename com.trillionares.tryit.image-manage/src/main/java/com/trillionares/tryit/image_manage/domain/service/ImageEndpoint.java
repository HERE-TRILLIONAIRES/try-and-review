package com.trillionares.tryit.image_manage.domain.service;

import com.trillionares.tryit.image_manage.domain.common.json.JsonUtils;
import com.trillionares.tryit.image_manage.presentation.dto.ImageIdResponseDto;
import com.trillionares.tryit.image_manage.presentation.dto.ImageInfoResquestDto;
import com.trillionares.tryit.image_manage.presentation.dto.ProductIdAndProductImageIdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageEndpoint {

    private final ImageService imageService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(groupId = "productImageManagement", topics = "saveImageToDB")
    public void saveImageToDB(String message) throws Exception {
        log.info("saveImageToDB : {}", message);

        ImageInfoResquestDto resquestDto = JsonUtils.fromJson(message, ImageInfoResquestDto.class);

        ImageIdResponseDto responseDto = imageService.createImage(resquestDto);

        log.info("Image ID: {}", responseDto.getImageId());


        ProductIdAndProductImageIdDto pidAndPimgidDto = ProductIdAndProductImageIdDto.of(resquestDto.getProductId(), responseDto.getImageId());

        String json = JsonUtils.toJson(pidAndPimgidDto);
        kafkaTemplate.send("updateImageIdOfProduct", json);
    }

}
