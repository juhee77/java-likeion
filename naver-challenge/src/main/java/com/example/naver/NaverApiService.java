package com.example.naver;

import com.example.naver.client.NaverRestClient;
import com.example.naver.dto.DirectionRequestDto;
import com.example.naver.dto.DirectionResponseDto;
import com.example.naver.dto.GeocodingRequestDto;
import com.example.naver.dto.GeocodingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverApiService {
    private final NaverRestClient naverRestClient;

    public DirectionResponseDto direction(DirectionRequestDto requestDto) throws IOException {
        return naverRestClient.getPath(requestDto);
    }

    public GeocodingResponseDto geocoding(GeocodingRequestDto requestDto) throws IOException {
        return naverRestClient.getGeoCoding(requestDto);
    }
}
