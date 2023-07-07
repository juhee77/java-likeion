package com.lahee.security.controller;

import com.lahee.security.dto.UserRegistrationModel;
import com.lahee.security.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String myProfile(Authentication authentication, Model model) {
        log.info("{} {}", authentication.getName(), authentication);
        log.info("security context holder : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info(userDetails.getUsername());
        log.info(userDetails.getPassword());
        model.addAttribute("username", authentication.getName());
        return "my-profile";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationModel", new UserRegistrationModel());
        return "register-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userRegistrationModel") UserRegistrationModel userRegistrationModel) {
        log.info("{} ", userRegistrationModel);
        String username = userRegistrationModel.getUsername();
        String password = userRegistrationModel.getPassword();
        String passwordCheck = userRegistrationModel.getPasswordCheck();

        if (passwordCheck.equals(password)) {
//            manager.createUser(User.withUsername(username).password(passwordEncoder.encode(password)).build()); //security context에 저장한댜
            manager.createUser(CustomUserDetails.builder().username(username).password(passwordEncoder.encode(password)).build()); //security context에 저장한댜
            return "redirect:/users/login";
        }

        log.warn("password does not match...");
        return "redirect:/users/register?error";
//        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

}