package com.lahee.webclient.client;

import com.lahee.webclient.dto.BeerGetDto;
import com.lahee.webclient.dto.BeerPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component //메인 비즈니스 로직은 아니기에 service는 없다.
@RequiredArgsConstructor
@Slf4j
public class BeerRestClient implements BeerClient {
    private final RestTemplate restTemplate;

    @Override
    public BeerGetDto getBeer() {
        String url = "https://random-data-api.com/api/v2/beers";
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        return response.getBody();
    }

    @Override
    public boolean postBeer(BeerPostDto dto) {
        String url = "http://localhost:8081/give-me-beer";

        // RestController
        ResponseEntity response = restTemplate.postForEntity(url, dto, String.class);
        log.info("{}", response.getStatusCode());
        log.info("{}", response.getBody());
        log.info("{}", response.getHeaders());
        return true;
    }
}
