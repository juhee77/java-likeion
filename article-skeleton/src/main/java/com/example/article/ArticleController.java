package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping
    public ArticleDto saveArticles(@RequestBody ArticleDto dto) {
        return service.createArticle(dto);
    }

    // GET /articles


    // GET /articles/{id}


    // PUT /articles/{id}


    // DELETE /articles/{id}

}
