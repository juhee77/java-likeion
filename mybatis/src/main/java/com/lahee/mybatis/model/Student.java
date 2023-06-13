package com.lahee.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
