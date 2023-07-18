package com.lagee.relation.school.controller;

import com.lagee.relation.school.dto.InstructorDto;
import com.lagee.relation.school.dto.LectureDto;
import com.lagee.relation.school.repository.InstructorRepository;
import com.lagee.relation.school.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lecture")
@RequiredArgsConstructor
@Slf4j
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    @PostMapping
    public LectureDto createLecture(@RequestBody LectureDto lectureDto) {
        return LectureDto.fromEntity(lectureRepository.save(lectureDto.newEntity()));
    }

    @PostMapping
    public InstructorDto createInstructor(@RequestBody InstructorDto instructorDto) {
        return InstructorDto.fromEntity(instructorRepository.save(instructorDto.newEntity()));
    }

    
}
