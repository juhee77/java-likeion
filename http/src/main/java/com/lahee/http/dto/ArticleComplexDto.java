package com.lahee.http.dto;

import lombok.Data;

import java.util.List;
/*
{
    "title": "JSON",
    "content": "Javascript Object Notation",
    "writer": {
        "name": "John",
        "address": "New York"
    },
    "comments": [
        "hello",
        "nice job",
        "have a good day"
    ],
    "unneeded": "value"
}
 */
@Data
public class ArticleComplexDto {
    private String title;
    private String content;
    private WriterDto writer;
    private List<String> comments;
}