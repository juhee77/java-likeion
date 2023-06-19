package com.lahee.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class HttpMethodController {
    // `/path`로 오는 GET 요청에 대해서 메소드를 실행하고 싶을때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.GET
    )
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

//    @RequestMapping(
//            value = "/path",
//            method = RequestMethod.POST
//    )
//    public String postPath() {
//        log.info("POST /path");
//        return "index";
//    }

    @RequestMapping(
            value = "/path",
            method = {RequestMethod.DELETE, RequestMethod.PUT}
    )
    public String putOrDeletePath() {
        log.info("Put or Delete /path");
        return "index";
    }

    @RequestMapping(
            value = "/path",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String postWithJsonPath() {
        log.info("POST with Json /path");
        return "index";
    }

    @RequestMapping(
            value = "/path",
            method = {RequestMethod.POST},
            headers = "x-likelion=hello"
    )
    public String postWithHeader() {

        log.info("POST with header x-likelion=hello /path");
        return "index";
    }


    /**
     * @param message -> param의 이름이 잚못입력되면 400에러(사용자의 요청이 잘못되었다) , 만약 아예없는 api 경로라면 404에러
     */
    @RequestMapping(
            value = "/path",
            method = {RequestMethod.POST}
    )
    public String postWithParam(@RequestParam("message") String message) {
        log.info("POST with Param = {}", message);
        return "index";
    }


    @ResponseBody
    @GetMapping("/") //다음처럼 바로 지정하는 방법도 있다.
    public String home(){
        return "this is Home";
    }
}
