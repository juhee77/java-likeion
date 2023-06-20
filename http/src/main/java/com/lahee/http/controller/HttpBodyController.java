package com.lahee.http.controller;

import com.lahee.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.lahee.http.dto.Status.SUCCESS;

@Controller
@Slf4j
public class HttpBodyController {
    @PostMapping("/body")
    public @ResponseBody ResponseDto body() {
        return ResponseDto.from(SUCCESS);
    }

    @PostMapping("/body-article")
    public @ResponseBody ResponseDto read(@RequestBody ArticleDto articleDto) {
        log.info("article : {}", articleDto);
        return ResponseDto.from(SUCCESS);
    }

    @PostMapping("/body-writer")
    @ResponseBody
    public ResponseDto write(@RequestBody WriterDto writerDto) {
        log.info("write : {}", writerDto);
        return ResponseDto.from(SUCCESS);
    }

    @PostMapping("/body-article-comment")
    @ResponseBody
    public ResponseDto articleComment(@RequestBody ArticleWithCommentsDto articleWithCommentsDto) {
        log.info("body-article-comment : {}", articleWithCommentsDto);
        return ResponseDto.from(SUCCESS);

    }

    @PostMapping("/body-article-comment-complex")
    @ResponseBody
    public ResponseDto articleWriterComment(@RequestBody ArticleComplexDto articleComplexDto) {
        log.info("body-article-comment-complex: {}", articleComplexDto);
        return ResponseDto.from(SUCCESS);
    }
}
