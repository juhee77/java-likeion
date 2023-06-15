package com.lahee.jpa.controller;

import com.lahee.jpa.dto.StudentRequestDto;
import com.lahee.jpa.dto.StudentResponseDto;
import com.lahee.jpa.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("studentList", studentService.readStudentAll());
        return "home";
    }

    // create.html 응답
    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    // 새로운 StudentEntity 생성 후 상세보기 페이지로
    @PostMapping("/create")
    public String create(@ModelAttribute StudentRequestDto studentRequestDto) {
        StudentResponseDto responseDto = studentService.createStudent(studentRequestDto);
        return "redirect:/students/" + responseDto.getId();
    }

    // id에 해당하는 StudentEntity의 read.html 응답
    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        StudentResponseDto studentResponseDto = studentService.readStudent(id);
        model.addAttribute("student", studentResponseDto);
        return "read";
    }

    // id에 해당하는 StudentEntity의 update.html 응답
    @GetMapping("/{id}/update-view")
    public String updateView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", studentService.readStudent(id));
        return "update";
    }

    // id에 해당하는 StudentEntity 수정 후 상세보기 페이지로
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, StudentRequestDto studentRequestDto) {
        studentService.updateStudent(id, studentRequestDto);
        return "redirect:/students/" + id;
    }

    // id에 해당하는 StudentEntity delete.html
    @GetMapping("/{id}/delete-view")
    public String deleteView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", studentService.readStudent(id));
        return "delete";
    }


    // id에 해당하는 StudentEntity 삭제 후 홈페이지로
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}