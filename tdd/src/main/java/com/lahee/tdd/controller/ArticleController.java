package com.lahee.tdd.controller;

import com.lahee.tdd.dto.ArticleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @GetMapping
    public List<ArticleDto> readArticles(@RequestParam("title") String title) {
        return new ArrayList<>();
    }
}
