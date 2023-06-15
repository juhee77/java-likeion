package com.lahee.jpa.service;

import com.lahee.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    // CREATE
    public void createStudent() {
    }

    // READ
    public void readStudent() {
    }

    // READ ALL
    public void readStudentAll() {
    }

    // UPDATE
    public void updateStudent() {
    }

    // DELETE
    public void deleteStudent() {
    }
}