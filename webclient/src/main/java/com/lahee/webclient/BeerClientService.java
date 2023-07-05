package com.lahee.webclient;

import com.lahee.webclient.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class BeerClientService {
    public void getBeer() {
        WebClient webClient = WebClient.builder().build();
        //builder 패턴 처럼 사용한다.

        String url = "https://random-data-api.com/api/v2/beers";
//        String header = webClient.get()  // GET 요청 준비 post()도 사용가능
//                .uri(url)    // 요청 URL 설정
//                .header("x-test", "header")//요청 헤더 추가
//                //body도 추가가능
//                .retrieve()  // 해당 위에부분은 요청 부분이다. 아래 부분은 응답에 대한 설정이다.
//                .bodyToMono(String.class)  // 단일 객체의 응답을 기대한다.
//                .block();
////        BeerGetDto dto = (BeerGetDto) header;    // 동기식 처리
//         log.info(header.toString());
        BeerGetDto dto = webClient.get()  // GET 요청 준비 post()도 사용가능
                .uri(url)    // 요청 URL 설정
                .header("x-test", "header")//요청 헤더 추가
                //body도 추가가능
                .retrieve()  // 해당 위에부분은 요청 부분이다. 아래 부분은 응답에 대한 설정이다.
                .bodyToMono(BeerGetDto.class)  // 단일 객체의 응답을 기대한다.
                .block();

        log.info(dto.toString());



    }
}
