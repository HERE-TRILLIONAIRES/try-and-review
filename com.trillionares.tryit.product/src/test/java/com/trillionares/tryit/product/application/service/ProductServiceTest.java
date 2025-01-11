package com.trillionares.tryit.product.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품 전체 조회 테스트")
    void getProduct() throws Exception {
//        // given
//        String url = "/api/products";
//
//        // when
//        MvcResult result = mockMvc.perform(get(url))
//                .andExpect(status().isOk()) // 상태 코드 200 확인
//                .andReturn();
//
//        // then
//        String responseBody = result.getResponse().getContentAsString(); // JSON 응답 추출
//        System.out.println("Response Body: " + responseBody);
//
//        // 추가 검증 (JSON 응답 검증)
//        assertNotNull(responseBody);
//        assertTrue(responseBody.contains("\"status\":200"));
    }

    @Test
    @DisplayName("특정 상품 조회 테스트")
    void getProductById() {
    }
}