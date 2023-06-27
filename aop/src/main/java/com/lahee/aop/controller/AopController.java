package com.lahee.aop.controller;

import com.lahee.aop.annotation.LogArguments;
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
    public ResponseDto addUser(@RequestBody UserDto userDto) {
        log.info("POST /users {}", userDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }

    @GetMapping("/users")
    @LogArguments
    public ResponseDto testUser() {
        log.info("GET /users ");
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }
}
