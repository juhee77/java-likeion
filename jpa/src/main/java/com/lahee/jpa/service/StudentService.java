package com.lahee.jpa.service;

import com.lahee.jpa.domain.StudentEntity;
import com.lahee.jpa.dto.StudentRequestDto;
import com.lahee.jpa.dto.StudentResponseDto;
import com.lahee.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    // CREATE
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        return StudentResponseDto.fromEntity(studentRepository.save(studentRequestDto.toEntity()));
    }

    // READ
    public StudentResponseDto readStudent(Long id) {
        return StudentResponseDto.fromEntity(getStudentEntityById(id));
    }

    private StudentEntity getStudentEntityById(Long id) {
//        Optional<StudentEntity> findStudent = studentRepository.findById(id);
//        if (findStudent.isPresent()) {
//            return findStudent.get();
//        }
//        throw new RuntimeException("없는 id의 회원 입니다.");
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("없는 ID 입니다."));
    }

    // READ ALL
    public List<StudentResponseDto> readStudentAll() {
        return convertToResponseDtoList(studentRepository.findAll());
    }

    private static List<StudentResponseDto> convertToResponseDtoList(List<StudentEntity> all) {
        return all.stream()
                .map(StudentResponseDto::fromEntity)
                .toList();
    }

    // UPDATE
    @Transactional
    public void updateStudent(Long id, StudentRequestDto studentRequestDto) {
        getStudentEntityById(id).update(studentRequestDto);
    }

    // DELETE
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}