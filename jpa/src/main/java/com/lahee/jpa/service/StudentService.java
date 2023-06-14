package com.lahee.jpa.service;

import com.lahee.jpa.domain.dto.StudentRequestDto;
import com.lahee.jpa.domain.dto.StudentResponseDto;
import com.lahee.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        return studentRepository.save(studentRequestDto.toEntity()).toResponseDto();
    }

    public List<StudentResponseDto> readStudentAll() {
        return studentRepository.findAll()
                .stream()
                .map(s -> new StudentResponseDto(s.getId(), s.getName(), s.getAge(), s.getPhone(), s.getEmail()))
                .collect(Collectors.toList());
    }

    public StudentResponseDto readStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("없는 회원 번호 입니다.")).toResponseDto();
    }

    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 회원 번호 입니다."))
                .update(studentRequestDto)
                .toResponseDto();
    }
}
