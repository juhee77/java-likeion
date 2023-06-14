package com.lahee.jpa.service;

import com.lahee.jpa.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 주된 비즈니스 로직이 일어나는 공간
 * 1. 데이터베이스 조회
 * 2. component 사용
 * 3. 모은 데이터를 가지고 응답 (의사결정)
 */
@Service
@RequiredArgsConstructor
public class AppService {
    private final AppRepository appRepository;

    public List<Object> readStudentAll() {
        return appRepository.selectStudentAll();
    }
}
