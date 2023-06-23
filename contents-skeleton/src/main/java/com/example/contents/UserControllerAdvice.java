package com.example.contents;

import com.example.contents.dto.ResponseDto;
import com.example.contents.exception.Status400Exception;
import com.example.contents.exception.Status404Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//전역적으로 발생하는 예외를 잡아준다.
@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {
    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
//     400에러 발생 시키기
    @ExceptionHandler(Status400Exception.class)
    public ResponseEntity<ResponseDto> handleIllegalState(Status400Exception e) {
        ResponseDto response = new ResponseDto();
        log.info("UserControllerAdvice에서 처리한 400 예외입니다.");
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    //    @ResponseStatus(HttpStatus.NOT_FOUND)
    // 404에러 발생 시키기
    @ExceptionHandler(Status404Exception.class)
    public ResponseEntity<ResponseDto> handleIllegalState(Status404Exception e) {
        ResponseDto response = new ResponseDto();
        log.info("UserControllerAdvice에서 처리한 404 예외입니다.");
        response.setMessage(e.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
