package com.example.naver.dto;

import lombok.Data;

@Data
public class GeocodingRequestDto {
    private Double lat;
    private Double lng;

    @Override
    public String toString() {
        return lng + "," + lat;

    }
}
