package com.lahee.http.dto;

import lombok.Data;
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
public class WriterDto {
    String name;
    Integer age;
    String address;
}
