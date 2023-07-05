package com.lahee.webclient.client;

import com.lahee.webclient.dto.BeerGetDto;
import com.lahee.webclient.dto.BeerPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component //메인 비즈니스 로직은 아니기에 service는 없다.
//@Qualifier("wc")
@Primary //우선적으로 빈을 넣어줄것이다.
@Slf4j
public class BeerWebClient implements BeerClient {
    @Override
    public BeerGetDto getBeer() {
        WebClient webClient = WebClient.builder().build();
        String url = "https://random-data-api.com/api/v2/beers";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(BeerGetDto.class)
                .block();
    }

    @Override
    public boolean postBeer(BeerPostDto requestDto) {
        String url = "http://localhost:8081/give-me-beer";

        //post 요청 시작
        WebClient webClient = WebClient.builder().build();
        BeerGetDto dto = webClient.post()
                .uri(url)    // 요청 URL 설정
                .bodyValue(requestDto)   //requestBody
                .retrieve()  // 응답 정의
                .bodyToMono(BeerGetDto.class)  // 응답 데이터 정의
                .block(); //동기식 처리(메서드 한번 처리할때만다 응답을 정의한다)

        log.info(dto.toString());
        return true;
    }
}
