package com.trillionares.tryit.product.recruitment.presentation.controller;

import com.trillionares.tryit.product.recruitment.application.service.RecruitmentService;
import com.trillionares.tryit.product.recruitment.presentation.dto.request.CreateRecruitmentRequest;
import com.trillionares.tryit.product.recruitment.presentation.dto.request.UpdateRecruitmentRequest;
import com.trillionares.tryit.product.recruitment.presentation.dto.response.RecruitmentResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<RecruitmentResponse> createRecruitment(
            @Valid @RequestBody CreateRecruitmentRequest request
    ) {
        return ResponseEntity.status(201).body(recruitmentService.createRecruitment(request));
    }

    @PatchMapping("{recruitmentId}")
    public ResponseEntity<RecruitmentResponse> updateRecruitment(
            @PathVariable UUID recruitmentId,
            @Valid @RequestBody UpdateRecruitmentRequest request
    ) {
        return ResponseEntity.ok(recruitmentService.updateRecruitment(recruitmentId, request));
    }


    @DeleteMapping("{recruitmentId}")
    public ResponseEntity<RecruitmentResponse> deleteRecruitment(
            @PathVariable UUID recruitmentId
    ) {
        return ResponseEntity.ok(recruitmentService.deleteRecruitment(recruitmentId));
    }


    @GetMapping
    public ResponseEntity<RecruitmentResponse> getRecruitment(
            @PathVariable UUID recruitmentId
    ) {
        return ResponseEntity.ok(recruitmentService.getListRecruitment(recruitmentId));
    }


}
