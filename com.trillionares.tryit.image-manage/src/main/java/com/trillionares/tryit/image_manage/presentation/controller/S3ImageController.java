package com.trillionares.tryit.image_manage.presentation.controller;

import com.trillionares.tryit.image_manage.domain.service.S3ImageService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        try{
            String imgUrl = s3ImageService.upload(image);
            return ResponseEntity.ok(imgUrl);
        } catch (RuntimeException re){
            return ResponseEntity.badRequest().body(re.getMessage());
        }
    }
}
