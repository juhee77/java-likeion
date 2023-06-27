package com.lahee.aop.controller;

import com.lahee.aop.annotation.LogArguments;
import com.lahee.aop.annotation.LogExecutionTime;
import com.lahee.aop.dto.ResponseDto;
import com.lahee.aop.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {

    @PostMapping("/users")
    @LogExecutionTime
    public ResponseDto addUser(@RequestBody UserDto userDto) {
        log.info("POST /users {}", userDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }

    @GetMapping("/users")
    @LogArguments
    @LogExecutionTime
    public ResponseDto testUser() {
        log.info("GET /users ");
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }


    @GetMapping("/test-time")
    @LogExecutionTime
    public ResponseDto testTime() {
        log.info("GET /test-time ");
        long startTime = System.currentTimeMillis();

        try {
            ResponseDto response = new ResponseDto();
            response.setMessage("success");
            return response;
        }finally {
            long endTime = System.currentTimeMillis();

            log.info("executed in : {}ms",  endTime - startTime);
        }
    }
}
