package com.lahee.jpa.domain;

import com.lahee.jpa.domain.dto.StudentResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username", nullable = false, unique = true)
    private String name;


    private Integer age;
    private String phone;
    private String email;

    public StudentResponseDto toResponseDto() {
        return new StudentResponseDto(id, name, age, phone, email);
    }
}