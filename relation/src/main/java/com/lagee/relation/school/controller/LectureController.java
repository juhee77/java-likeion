package com.lagee.relation.school.controller;

import com.lagee.relation.school.dto.LectureDto;
import com.lagee.relation.school.dto.StudentDto;
import com.lagee.relation.school.entity.Lecture;
import com.lagee.relation.school.entity.Student;
import com.lagee.relation.school.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lecture")
@RequiredArgsConstructor
@Slf4j
public class LectureController {
    private final LectureRepository lectureRepository;

    @PostMapping
    public LectureDto createLecture(@RequestBody LectureDto lectureDto) {
        return LectureDto.fromEntity(lectureRepository.save(lectureDto.newEntity()));
    }

    @GetMapping("{id}/students")
    public List<StudentDto> updateStudentLectures(@PathVariable("id") Long id) {
        log.info("id :{}",id);
        Optional<Lecture> lectures = lectureRepository.findById(id);
        if (lectures.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : lectures.get().getStudents()) {
            studentDtos.add(StudentDto.fromEntity(student));
        }
        return studentDtos;
    }

}
