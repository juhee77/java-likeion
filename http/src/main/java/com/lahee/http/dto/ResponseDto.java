package com.lahee.http.dto;

import lombok.Data;

//응답 (상태, 메시지)
/*
{
    "status" : 200,
    "message": "success"
}
 */
@Data
public class ResponseDto {
    int status;
    String message;
}
