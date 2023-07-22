package com.lahee.tdd.controller;

import com.lahee.tdd.dto.ArticleDto;
import com.lahee.tdd.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @GetMapping
    public List<ArticleDto> readArticles(@RequestParam("title") String title) {
        return service.findByTitle(title);
    }
}
