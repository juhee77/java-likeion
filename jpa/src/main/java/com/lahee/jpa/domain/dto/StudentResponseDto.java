package com.lahee.jpa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
