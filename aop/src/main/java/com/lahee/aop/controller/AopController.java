package com.lahee.aop.controller;

import com.lahee.aop.dto.ResponseDto;
import com.lahee.aop.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {

    @PostMapping("/users")
    public ResponseDto addUser(@RequestBody UserDto userDto) {
        log.info("{}", userDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }
}
