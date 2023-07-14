package com.example.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
    private Double lat;
    private Double lng;

    @Override
    public String toString() {
        return lng + "," + lat;
    }
}
