package com.lagee.relation.school.controller;

import com.lagee.relation.school.dto.LectureDto;
import com.lagee.relation.school.entity.Instructor;
import com.lagee.relation.school.entity.Lecture;
import com.lagee.relation.school.repository.InstructorRepository;
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
@RequestMapping("instructor")
@RequiredArgsConstructor
@Slf4j
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

//    @GetMapping("{id}/lectures")
//    public void readInstructorLectures(
//            @PathVariable("id") Long id
//    ) {
//        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
//        Instructor instructor = optionalInstructor.get();
//        for (Lecture lecture : instructor.getLectures()) {
//            log.info(lecture.getName());
//        }
//    }

    @GetMapping("{id}/lectures")
    public List<LectureDto> readInstructorLectures(@PathVariable("id") Long id) {
        List<LectureDto> lectureList = new ArrayList<>();
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Instructor instructor = optionalInstructor.get();
        List<Lecture> lectureEntities = lectureRepository.findAllByInstructor(instructor);
        for (Lecture lecture : lectureEntities)
            lectureList.add(LectureDto.fromEntity(lecture));

        return lectureList;
    }
}
