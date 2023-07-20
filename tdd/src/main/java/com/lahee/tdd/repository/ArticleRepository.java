package com.lahee.tdd.repository;

import com.lahee.tdd.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTitleContains(String test);
}