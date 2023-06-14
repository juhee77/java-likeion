package com.lahee.jpa.controller.api;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import com.lahee.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/read/{id}")
    public StudentResponseDto readOne(@PathVariable("id") Long id) {
        return studentService.readStudent(id);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<StudentResponseDto>> readAll() {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentAll();
        return ResponseEntity.ok(studentResponseDtos);
    }
}
