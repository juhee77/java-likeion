package com.example.naver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class WebClientConfig {
    @Value("${ncp.api.client-id}")
    private String ncpApiClientId;

    @Value("${ncp.api.client-secret}")
    private String ncpApiClientSecret;
}
