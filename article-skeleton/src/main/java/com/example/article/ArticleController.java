package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping
    public List<ArticleDto> getArticles() {
        return service.readArticleAll();
    }

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto getArticleOptional(@PathVariable("id") Long id) {
        return service.readArticleOptional(id);
    }

    // GET /articles/page-test
    @GetMapping("/page-test")
    public List<ArticleDto> getArticlePaged() {
        return service.readArticlesPaged();
    }

    // GET /articles/page-test/{id}
    @GetMapping("/page-test/{id}")
    public List<ArticleDto> getArticleUnderIdPaged(@PathVariable("id") Long id) {
        return service.readArticleUnderIdPaged(id);
    }

    // GET /articles/page-test-pageable
    @GetMapping("/page-test-pageable")
    public Page getArticleUnderIdPagable() {
        return service.readArticlesPageable();
    }


    // GET /articles/page-param
    @GetMapping("/page-param")
    public Page<ArticleDto> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit) {
        return service.readArticlePaged(page, limit);
    }


    @GetMapping("/search")
    public Page<ArticleDto> search(
            @RequestParam("query") String query, //검색어 필수
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber) {

        return service.findAllByTitleContains(query, pageNumber);
    }

    @GetMapping("/search/example")
    public Page<ArticleDto> searchByExample(
            @RequestParam("query") String query, //검색어 필수
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber) {

        return service.findAllByExample(query, pageNumber);
    }


    @GetMapping("/my/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") Long id) {
        ResponseEntity response;
        try {
            ArticleDto articleDto = service.readArticle(id);
            response = ResponseEntity.ok(articleDto);
        } catch (RuntimeException e1) {
            response = ResponseEntity.notFound().header("x-like", "none").build();
        }
        return response;
    }


    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDto dto) {
        return service.updateArticle(id, dto);
    }


    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        service.deleteArticle(id);
    }
}
