package com.example.contents;

import com.example.contents.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//전역적으로 발생하는 예외를 잡아준다.
@Slf4j
@RestControllerAdvice  // @ControllerAdvice
// 각 Controller에 나뉘어진 ExceptionHandler 메소드를 모으는 용도
public class UserControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseDto handleIllegalState(IllegalStateException exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage("UserControllerAdvice에서 처리한 예외입니다.");
        return response;
    }
}
