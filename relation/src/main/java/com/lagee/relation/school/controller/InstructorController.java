package com.lagee.relation.school.controller;

import com.lagee.relation.school.entity.Instructor;
import com.lagee.relation.school.entity.Lecture;
import com.lagee.relation.school.repository.InstructorRepository;
import com.lagee.relation.school.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사를 배정한다
    @PutMapping("{id}/instructor/{instructorId}")
    // 응답 바디가 없을 것이다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLectureInstructor(
            @PathVariable("id") Long id,
            @PathVariable("instructorId") Long instructorID
    ) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorID);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Lecture lecture = optionalLecture.get();
        Instructor instructor = optionalInstructor.get();
        // 그냥 Java 객체 쓰듯이
        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }
}
