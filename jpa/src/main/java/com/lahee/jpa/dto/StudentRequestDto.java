package com.lahee.jpa.dto;

import com.lahee.jpa.domain.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
