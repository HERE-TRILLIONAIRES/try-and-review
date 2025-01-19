package com.trillionares.tryit.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayCorsConfig {

    @Value("${dns.name.auth-service}")
    private String dnsNameAuth;
    @Value("${dns.name.trial-service}")
    private String dnsNameTrial;
    @Value("${dns.name.product-service}")
    private String dnsNameProduct;
    @Value("${dns.name.review-service}")
    private String dnsNameReview;
    @Value("${dns.name.statistics-service}")
    private String dnsNameStatistics;
    @Value("${dns.name.notification-service}")
    private String dnsNameNotification;
    @Value("${dns.name.image-manage-service}")
    private String dnsNameImageManage;
    @Value("${dns.name.config-service}")
    private String dnsNameConfig;
    @Value("${dns.name.gateway-service}")
    private String dnsNameGateway;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(dnsNameAuth); // 허용할 도메인
        config.addAllowedOrigin(dnsNameTrial); // 허용할 도메인
        config.addAllowedOrigin(dnsNameProduct); // 허용할 도메인
        config.addAllowedOrigin(dnsNameReview); // 허용할 도메인
        config.addAllowedOrigin(dnsNameStatistics); // 허용할 도메인
        config.addAllowedOrigin(dnsNameNotification); // 허용할 도메인
        config.addAllowedOrigin(dnsNameImageManage); // 허용할 도메인
        config.addAllowedOrigin(dnsNameConfig); // 허용할 도메인
        config.addAllowedOrigin(dnsNameGateway); // 허용할 도메인
        config.addAllowedMethod("*"); // 모든 메서드 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}


