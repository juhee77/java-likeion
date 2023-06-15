package com.lahee.jpa.dto;

import com.lahee.jpa.domain.StudentEntity;
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

    //정적 팩토리 메소드 패턴
    public static StudentResponseDto fromEntity(StudentEntity student) {
       return new StudentResponseDto(student.getId(), student.getName(), student.getAge(), student.getPhone(), student.getEmail());
    }
}
