package com.lahee.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//응답 (상태, 메시지)
/*
{
    "status" : 200,
    "message": "success"
}
 */
@Data
@AllArgsConstructor
public class ResponseDto {
    int status;
    String message;

    public static ResponseDto from(Status status) {
        return status.responseDto;
    }
}
