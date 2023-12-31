package com.lahee.jpa.service;

import com.lahee.jpa.domain.StudentEntity;
import com.lahee.jpa.dto.StudentRequestDto;
import com.lahee.jpa.dto.StudentResponseDto;
import com.lahee.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeroreStudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        return studentRepository.save(studentRequestDto.toEntity()).toResponseDto();
    }

    public List<StudentResponseDto> readStudentAll() {
        return convertToResponseDto(studentRepository.findAll());
    }

    public List<StudentResponseDto> readStudentByAgeDesc() {
        return convertToResponseDto(studentRepository.findAllByOrderByAgeDesc());
    }

    public List<StudentResponseDto> readStudentByAge() {
        return convertToResponseDto(studentRepository.findAllByOrderByAge());
    }

    public List<StudentResponseDto> readStudentByUnderAge(int age) {
        return convertToResponseDto(studentRepository.findAllByAgeLessThan(age));
    }

    public List<StudentResponseDto> readStudentByPhoneStatingWith(String regx) {
        return convertToResponseDto(studentRepository.findAllByPhoneStartingWith(regx));
    }

    private static List<StudentResponseDto> convertToResponseDto(List<StudentEntity> allByOrderByAgeDesc) {
        return allByOrderByAgeDesc
                .stream()
                .map(StudentResponseDto::fromEntity)
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

    @Transactional
    public void deleteStudent(Long id) {
        Optional<StudentEntity> find = studentRepository.findById(id);
        if (find.isEmpty()) {
            throw new RuntimeException("없는 ID입니다.");
        }
        //delete(find.get()))
        studentRepository.deleteById(id);
    }
}
