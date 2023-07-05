package com.lahee.webclient.service;

import com.lahee.webclient.dto.BeerGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component //메인 비즈니스 로직은 아니기에 service는 없다.
@RequiredArgsConstructor
public class BeerWebClient {
    public BeerGetDto getBeer() {
        WebClient webClient = WebClient.builder().build();
        String url = "https://random-data-api.com/api/v2/beers";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(BeerGetDto.class)
                .block();
    }
}
