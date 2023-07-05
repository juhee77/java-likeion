package com.lahee.webclient;

import com.lahee.webclient.dto.BeerGetDto;
import com.lahee.webclient.dto.BeerPostDto;
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

        //String
        String header = webClient.get()  // GET 요청 준비 post()도 사용가능
                .uri(url)    // 요청 URL 설정
                .header("x-test", "header")//요청 헤더 추가
                //body도 추가가능
                .retrieve()  // 해당 위에부분은 요청 부분이다. 아래 부분은 응답에 대한 설정이다.
                .bodyToMono(String.class)  // 단일 객체의 응답을 기대한다.
                .block();// 동기식 처리
         log.info(header.toString());

//        //DTO
//        BeerGetDto dto = webClient.get()  // GET 요청 준비 post()도 사용가능
//                .uri(url)    // 요청 URL 설정
//                .header("x-test", "header")//요청 헤더 추가
//                .retrieve()  // 해당 위에부분은 요청 부분이다. 아래 부분은 응답에 대한 설정이다.
//                .bodyToMono(BeerGetDto.class)  // 단일 객체의 응답을 기대한다.
//                .block(); //동기식 처리
//
//        log.info(dto.toString());
    }

    public void postBeer() {
        WebClient webClient = WebClient.builder().build();
        //builder 패턴 처럼 사용한다.

        String url = "http://localhost:8081/give-me-beer";
        BeerPostDto requestDto = new BeerPostDto("hehe", 123L, 4.5);

        //post 요청 시작
        BeerGetDto dto = webClient.post()
                .uri(url)    // 요청 URL 설정
                .bodyValue(requestDto)   //requestBody
                .retrieve()  // 응답 정의
                .bodyToMono(BeerGetDto.class)  // 응답 데이터 정의
                .block(); //동기식 처리(메서드 한번 처리할때만다 응답을 정의한다)

        log.info(dto.toString());
    }
}
