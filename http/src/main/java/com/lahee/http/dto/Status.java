package com.lahee.http.dto;

public enum Status {
    SUCCESS(new ResponseDto(200,"success!")), FAIL(new ResponseDto(400, "fail!")),;
    ResponseDto responseDto;

    Status(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

}