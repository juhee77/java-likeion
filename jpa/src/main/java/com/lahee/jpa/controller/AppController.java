package com.lahee.jpa.controller;

import com.lahee.jpa.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @RequestMapping("students")
    public void students(){
        List<Object> result = appService.readStudentAll();
    }

    @GetMapping("/response-body")
    public @ResponseBody String responseBodyTest() {
        return "test";
    }

    @GetMapping("/home")
    public @ResponseBody String home() {
        return "HOME";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

}
