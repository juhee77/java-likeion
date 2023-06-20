package com.lahee.http.controller;

import com.lahee.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

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

    @PostMapping("/entity")
    public ResponseEntity<ResponseDto> entity(@RequestBody ArticleDto articleDto) {
        log.info("POST /entity : {} ", articleDto);
        //ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(ResponseDto.from(SUCCESS), HttpStatus.OK);
        //커스텀 헤더 만들고 함께 응답하기

        HttpHeaders httpHeaders = new HttpHeaders(CollectionUtils.toMultiValueMap(new LinkedCaseInsensitiveMap<>(8, Locale.ENGLISH)));
        httpHeaders.add("x-likelion-custom", "Hello Like Lion");
        return new ResponseEntity<>(ResponseDto.from(SUCCESS), httpHeaders, HttpStatus.ACCEPTED);
    }

    @PostMapping("/entity-more-header")
    public ResponseEntity<ResponseDto> entityMoreHeader(@RequestBody ArticleDto articleDto) {
        log.info("POST /entity : {} ", articleDto);

        HttpHeaders httpHeaders = new HttpHeaders(CollectionUtils.toMultiValueMap(new LinkedCaseInsensitiveMap<>(8, Locale.ENGLISH)));
        httpHeaders.add("x-likelion-custom", "Hello Like Lion");

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("x-likelion-custom2", "it's second")
                .headers(httpHeaders)
                .body(ResponseDto.from(SUCCESS));

    }

}
