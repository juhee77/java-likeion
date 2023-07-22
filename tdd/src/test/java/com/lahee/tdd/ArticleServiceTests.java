package com.lahee.tdd;

import com.lahee.tdd.domain.Article;
import com.lahee.tdd.dto.ArticleDto;
import com.lahee.tdd.repository.ArticleRepository;
import com.lahee.tdd.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTests {
    @InjectMocks //mock이 붙은 객체를 주입할 수 있다.
    private ArticleService articleService;

    @Mock //목 객체를 생성한다.
    private ArticleRepository articleRepository;

    @Test
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
        assertThat(articleService).isNotNull();
    }

    @Test
    // 제목의 유사도를 통해 조회
    public void searchByTitle() {
        // given
        String query = "Title";
        Article found = new Article("Title", "Contents");
        // mock
        when(articleRepository.findAllByTitleContains(query)).thenReturn(Collections.singletonList(found));

        // when
        // ArticleService 가 어떻게 동작하면 하는지
        List<ArticleDto> articleDtoList = articleService.findByTitle(query);

        // then
        assertEquals(1, articleDtoList.size());
        assertTrue(articleDtoList.get(0).getTitle().contains(query));

    }

}
