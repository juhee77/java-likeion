package com.lahee.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerPostDto {
    private String name;
    private Long cc;
    private Double alcohol;
}