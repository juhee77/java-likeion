package com.lagee.relation.school.controller;

import com.lagee.relation.school.dto.StudentDto;
import com.lagee.relation.school.entity.Lecture;
import com.lagee.relation.school.entity.Student;
import com.lagee.relation.school.repository.LectureRepository;
import com.lagee.relation.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @PostMapping
    public StudentDto createInstructor(@RequestBody StudentDto studentDto) {
        return StudentDto.fromEntity(studentRepository.save(studentDto.newEntity()));
    }

    @PutMapping("{id}/lectures/{lectureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudentLectures(@PathVariable("id") Long id, @PathVariable("lectureId") Long lectureId) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Student student = optionalStudent.get();
        Lecture lecture = optionalLecture.get();

        student.getAttending().add(lecture);
        studentRepository.save(student);
    }

    @DeleteMapping("{id}/lectures/{lectureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //응답이 없다 출력
    public void deleteStudentLectures(@PathVariable("id") Long id, @PathVariable("lectureId") Long lectureId) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Student student = optionalStudent.get();
        Lecture lecture = optionalLecture.get();

        student.getAttending().remove(lecture); //여러개 있으면 하나만 삭제한다.
        studentRepository.save(student);
    }

}