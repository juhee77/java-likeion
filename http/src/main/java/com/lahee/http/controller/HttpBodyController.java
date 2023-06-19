package com.lahee.http.controller;

import com.lahee.http.dto.ArticleDto;
import com.lahee.http.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HttpBodyController {
    @PostMapping("/body")
    public @ResponseBody ResponseDto body() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("success!");
        return responseDto;
    }

    @PostMapping("/body-article")
    public @ResponseBody ResponseDto read(@RequestBody ArticleDto articleDto) {
        log.info("article : {}", articleDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("success!");
        return responseDto;
    }
}
