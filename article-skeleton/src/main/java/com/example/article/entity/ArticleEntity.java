package com.example.article.entity;

import com.example.article.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String title;
    private String content;


    public static ArticleEntity fromDto(ArticleDto dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setWriter(dto.getWriter());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }

    public void update(ArticleDto dto) {
        this.writer = dto.getWriter();
        this.content = dto.getContent();
        this.title = dto.getTitle();
    }
}
