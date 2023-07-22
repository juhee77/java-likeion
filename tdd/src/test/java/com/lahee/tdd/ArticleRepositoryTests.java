package com.lahee.tdd;


import com.lahee.tdd.domain.Article;
import com.lahee.tdd.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    // articleRepository를 생성하여 데이터베이스 소통의 창구를 마련
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void createArticle() {
        //given
        Article article = new Article("test", "contents");

        //when
        Article save = articleRepository.save(article);

        //then
        assertThat(save.getTitle()).isEqualTo(article.getTitle());
        assertThat(save.getId()).isNotNull();
    }

    /*
      @Test
    public void createArticle() {
        // given
        Article article = new Article();
        article.setTitle("Hello!");
        article.setContent("TDD is hard");

        // when
        article = articleRepository.save(article);

        // then
        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();  //이런식으로 직접 문자열을 검사하는게 맞는지 아니면 위에처럼 저장 객체로 해도 되는지
        assertEquals("Hello!", article.getTitle());
        assertEquals("TDD is hard", article.getContent());
    }
     */

    @Test
    public void findByTitleContains() {
        //given
        articleRepository.save(new Article("test", "contents"));
        articleRepository.save(new Article("hello", "contents"));
        articleRepository.save(new Article("heeling", "contents"));

        //when
        List<Article> foundTitle1 = articleRepository.findAllByTitleContains("test");
        List<Article> foundTitle2 = articleRepository.findAllByTitleContains("a");
        List<Article> foundTitle3 = articleRepository.findAllByTitleContains("he");

        //then
        assertThat(foundTitle1.size()).isEqualTo(1);
        assertThat(foundTitle1.get(0).getTitle()).isEqualTo("test");
        assertThat(foundTitle2.size()).isEqualTo(0);
        assertThat(foundTitle3.size()).isEqualTo(2);
    }
}