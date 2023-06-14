package com.lahee.jpa.controller.api;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import com.lahee.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentApiController {
    private final StudentService studentService;

    @PostMapping("/create")
    public StudentResponseDto create(@RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto student = studentService.createStudent(studentRequestDto);
        return student;
    }
}
