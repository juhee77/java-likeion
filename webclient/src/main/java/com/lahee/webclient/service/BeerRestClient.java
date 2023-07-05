package com.lahee.webclient.service;

import com.lahee.webclient.dto.BeerGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component //메인 비즈니스 로직은 아니기에 service는 없다.
@RequiredArgsConstructor
public class BeerRestClient {
    private final RestTemplate restTemplate;

    public BeerGetDto getBeer() {
        String url = "https://random-data-api.com/api/v2/beers";
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        return response.getBody();
    }
}
