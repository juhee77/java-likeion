package com.lahee.jpa.controller.api;

import com.lahee.jpa.dto.StudentRequestDto;
import com.lahee.jpa.dto.StudentResponseDto;
import com.lahee.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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

    @PostMapping("/update/{id}")
    public StudentResponseDto update(@PathVariable("id") Long id, @RequestBody StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    @GetMapping("/update/{id}")
    public StudentResponseDto update(@PathVariable("id") Long id) {
        return studentService.updateStudent(id, new StudentRequestDto("바뀜~!", 123, "010-0987-4321", "modify@naver.com"));
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteTest(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
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

    @GetMapping("/read-by-age")
    public ResponseEntity<List<StudentResponseDto>> readByAge() {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentByAge().subList(0, 5);
        return ResponseEntity.ok(studentResponseDtos);
    }

    @GetMapping("/read-by-age-desc")
    public ResponseEntity<List<StudentResponseDto>> readByAgeDesc() {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentByAgeDesc().subList(0,5);
        return ResponseEntity.ok(studentResponseDtos);
    }
    @GetMapping("/read-by-age-under/{age}")
    public ResponseEntity<List<StudentResponseDto>> readByAgeUnder(@PathVariable("age") int age) {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentByUnderAge(age).subList(0,5);
        return ResponseEntity.ok(studentResponseDtos);
    }
    @GetMapping("/read-phone/{regx:.+}")
    public ResponseEntity<List<StudentResponseDto>> readPhone(@PathVariable("regx")String regx) {
        List<StudentResponseDto> studentResponseDtos = studentService.readStudentByPhoneStatingWith(regx).subList(0,5);
        return ResponseEntity.ok(studentResponseDtos);
    }
}
