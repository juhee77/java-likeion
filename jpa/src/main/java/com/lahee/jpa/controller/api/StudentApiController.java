package com.lahee.jpa.controller.api;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import com.lahee.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentApiController {
    private final StudentService studentService;

    @PostMapping("/create")
    public StudentResponseDto create(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.createStudent(studentRequestDto);
    }

    @GetMapping("/create")
    public StudentResponseDto create() {
        return studentService.createStudent(new StudentRequestDto("juhee", 25, "010-1234-5678", "bb@naver.com"));
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<StudentResponseDto>> readAll() {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentAll();
        return ResponseEntity.ok(studentResponseDtos);
    }
}
