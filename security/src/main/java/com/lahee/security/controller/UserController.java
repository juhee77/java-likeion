package com.lahee.security.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Slf4j
@Controller  // 로그인 페이지를 보여줄려고
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    // 1. login 페이지로 온다.
    // 2. login 페이지에 아이디 비밀번호를 입력한다.
    // 3. 성공하면 my-profile 로 이동한다.

    // 로그인 페이지를 위한 GetMapping
    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    // 로그인 성공 후 로그인 여부를 판단하기 위한 GetMapping
    @GetMapping("/my-profile")
    public String myProfile() {
        return "my-profile";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register-form";
    }

//    @PostMapping("/register")
//    public String register(Model model) {
//        String username = (String) model.getAttribute("username");
//        String password = (String) model.getAttribute("password");
//        String rePassword = (String) model.getAttribute("password-check");
//
//        log.info("{}{}{}",username,password,rePassword);
//
//        return "redirect:/users/login";
//    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password
            , @RequestParam("password-check") String passwordCheck) {
        log.info("{} {} {}", username, password, passwordCheck);
        if (passwordCheck.equals(password)) {
            manager.createUser(User.withUsername(username).password(passwordEncoder.encode(password)).build()); //security context에 저장한댜
            return "redirect:/users/login";
        }
        log.warn("password does not match...");
        return "redirect:/users/register?error";
//        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

}