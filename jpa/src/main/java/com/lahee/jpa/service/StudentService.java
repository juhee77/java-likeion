package com.lahee.jpa.service;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import com.lahee.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        return studentRepository.save(studentRequestDto.toEntity()).toResponseDto();
    }
}
