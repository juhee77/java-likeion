package com.lahee.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Controller
@Slf4j
public class HttpHeaderController {

    @PostMapping("/header-one")
    public String getHeader(@RequestHeader("x-likelion") String header, Model model) {
        log.info("POST /header-one header : {}",header);
        model.addAttribute("existingValue", header);
        return "index";
    }

    @PostMapping("/header-optional")
    public String getHeaderOptional(@RequestHeader(value = "x-likelion", required = false) String header, Model model) {
        log.info("POST /header-optional : {}",header);
        model.addAttribute("existingValue", header);
        return "index";
    }


    @PostMapping("/header-type")
    public String getHeaderType(@RequestHeader(value = "x-likelion", required = false)Integer header, Model model) {
        //만약 문자열 들어가면 400 bad request
        //만약 null을 넣고 싶으면 int -> Integer로 변경
        log.info("POST /header-type : {}",header);
        model.addAttribute("existingValue", header);
        return "index";
    }

    @PostMapping("/header-type-int")
    public String getHeaderType(@RequestHeader(value = "x-likelion") int header, Model model) {
        //null이 들어오지 못한다.
        log.info("POST /header-type : {}",header);

        model.addAttribute("existingValue", header);
        return "index";
    }

    @PostMapping("/header-all")
    public String getAllHeader(@RequestHeader Map<String, String> headerMap) {//모든 헤더 확인
        //null이 들어오지 못한다.
        log.info("START : POST /header-all");
        for (String s : headerMap.keySet()) {
            log.info("{} : {}", s, headerMap.get(s));
        }
        log.info("END : POST /header-all");

        return "index";
    }
}
