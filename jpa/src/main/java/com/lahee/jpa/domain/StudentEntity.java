package com.lahee.jpa.domain;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
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

    public StudentEntity update(StudentRequestDto studentRequestDto) {
        this.age = studentRequestDto.getAge();
        this.name = studentRequestDto.getName();
        this.phone = studentRequestDto.getPhone();
        this.email = studentRequestDto.getEmail();
        return this;
    }
}