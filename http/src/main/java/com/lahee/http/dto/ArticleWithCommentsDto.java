package com.lahee.http.dto;

import lombok.Data;

import java.util.List;

//블로그 게시글(제목, 내용)
/*
{
    "title": "JSON",
    "content": "Javascript Object Notation",
    "writer": "John",
    "comments": [
        "hello",
        "nice job",
        "have a good day"
    ]
}
 */

@Data
public class ArticleWithCommentsDto {
    String title;
    String content;
    String writer;
    List<String> comments;
}
