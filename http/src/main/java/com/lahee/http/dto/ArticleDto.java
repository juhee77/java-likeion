package com.lahee.http.dto;

import lombok.Data;

//블로그 게시글(제목, 내용)
/*
{
    "title" : "제목"
    "content" : "내용"
}
 */
@Data
public class ArticleDto {
    String title;
    String content;
}
