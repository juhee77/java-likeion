package com.lahee.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    // http://localhost:8080/
    @GetMapping
    public String root() {
        return "hello";
    }

    // http://localhost:8080/no-auth
    // no-auth 는 누구나 접근 가능하도록
    @GetMapping("/no-auth")
    public String noAuth() {
        return "no auth success!";
    }

    // http://localhost:8080/re-auth
    // re-auth 는 인증된 사용자만 접근 가능하도록
    @GetMapping("/re-auth")
    public String reAuth() {
        return "re auth success!";
    }
}