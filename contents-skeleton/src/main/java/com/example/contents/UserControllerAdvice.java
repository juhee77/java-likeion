package com.example.contents;

import com.example.contents.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//전역적으로 발생하는 예외를 잡아준다.
@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseDto handleIllegalState(RuntimeException exception) {
        return new ResponseDto("User Controller Advice 에서 처리한 예외 입니다. ");
    }
}
