package com.example.naver;

import com.example.naver.dto.DirectionRequestDto;
import com.example.naver.dto.DirectionResponseDto;
import com.example.naver.dto.GeocodingRequestDto;
import com.example.naver.dto.GeocodingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class NaverApiController {
    private final NaverApiService service;

    @PostMapping("direction")
    public DirectionResponseDto direction(@RequestBody DirectionRequestDto requestDto) throws IOException {
        return service.direction(requestDto);
    }

    @PostMapping("geocoding")
    public GeocodingResponseDto geocoding(@RequestBody GeocodingRequestDto requestDto) throws IOException {
        return service.geocoding(requestDto);
    }
}
