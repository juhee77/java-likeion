package com.example.naver.client;

import com.example.naver.config.WebClientConfig;
import com.example.naver.dto.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Component
public class NaverRestClient {
    private static final String NAVER_KEY_ID = "X-NCP-APIGW-API-KEY-ID";
    private static final String NAVER_KEY_SECRET = "X-NCP-APIGW-API-KEY";
    private static final String BASE_URL = "https://naveropenapi.apigw.ntruss.com";

    public static final WebClient WEB_CLIENT = WebClient.builder()
            .filter((request, next) -> {
                log.info("Request URL: {}", request.url()); //전송 url 을 확인하기 위해서
                return next.exchange(request);
            })
            .baseUrl(BASE_URL)
            .build();
    private final WebClientConfig config;


    public DirectionResponseDto getPath(DirectionRequestDto dto) throws IOException {

        String url = "/map-direction/v1/driving";

        String response = WEB_CLIENT.get()
                .uri(urlBuilder -> urlBuilder
                        .path(url)
                        .queryParam("start", dto.getStart())
                        .queryParam("goal", dto.getGoal())
                        .build())
                .header(NAVER_KEY_ID, config.getNcpApiClientId())
                .header(NAVER_KEY_SECRET, config.getNcpApiClientSecret())
                .retrieve()
                .bodyToMono(String.class)
                .block();


        // ObjectMapper 인스턴스 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 파싱
        JsonNode root = objectMapper.readTree(response);

        // "route" 필드의 "traoptimal" 배열 가져오기
        JsonNode traoptimalArray = root.get("route").get("traoptimal");

        // "traoptimal" 배열의 첫 번째 요소 가져오기
        JsonNode firstTraoptimal = traoptimalArray.get(0);

        // "path" 필드 가져오기
        JsonNode pathNode = firstTraoptimal.get("path");

        // "path" 배열을 List<List<Double>>로 변환
        DirectionResponseDto directionResponseDto = new DirectionResponseDto(new ArrayList<>());
        for (JsonNode pointNode : pathNode) {
            double longitude = pointNode.get(0).asDouble();
            double latitude = pointNode.get(1).asDouble();
            Point point = new Point(latitude, longitude);
            directionResponseDto.getPath().add(point);
        }


        return directionResponseDto;
    }


    public GeocodingResponseDto getGeoCoding(GeocodingRequestDto dto) throws IOException {
        String url = "/map-reversegeocode/v2/gc";

        String response = WEB_CLIENT.get()
                .uri(urlBuilder -> urlBuilder
                        .path(url)
                        .queryParam("coords", dto)
                        .queryParam("output", "json")
                        .build())
                .header(NAVER_KEY_ID, config.getNcpApiClientId())
                .header(NAVER_KEY_SECRET, config.getNcpApiClientSecret())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // ObjectMapper 인스턴스 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 파싱
        JsonNode root = objectMapper.readTree(response);

        // "results" 배열 가져오기
        JsonNode traoptimalArray = root.get("results");

        // "results" 배열의 첫 번째 요소 가져오기
        JsonNode firstTraoptimal = traoptimalArray.get(0);

        // "region" 필드 가져오기
        JsonNode pathNode = firstTraoptimal.get("region");

        // area의 name 불러와서 붙이기
        StringBuilder address = new StringBuilder();
        for (JsonNode pointNode : pathNode) {
            String name = pointNode.get("name").asText();
            address.append(name).append(" ");
        }

        return new GeocodingResponseDto(address.toString());
    }

}
