package com.lahee.tdd.service;

import com.lahee.tdd.domain.Article;
import com.lahee.tdd.dto.ArticleDto;
import com.lahee.tdd.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDto> findByTitle(String query) {
        List<ArticleDto> articleList = new ArrayList<>();
        for (Article article:
                articleRepository.findAllByTitleContains(query)) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setId(article.getId());
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContents());
            articleList.add(articleDto);
        }
        return articleList;
    }
}