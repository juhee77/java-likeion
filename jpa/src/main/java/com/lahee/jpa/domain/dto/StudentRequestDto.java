package com.lahee.jpa.domain.dto;

import com.lahee.jpa.domain.StudentEntity;
import lombok.Data;

@Data
public class StudentRequestDto {
    private String name;
    private Integer age;
    private String phone;
    private String email;

    public StudentEntity toEntity() {
        return StudentEntity.builder()
                .name(name)
                .age(age)
                .phone(phone)
                .email(email).build();
    }
}
