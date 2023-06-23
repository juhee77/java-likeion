package com.example.contents.exception;

public class InvalidPhoneNumberException extends Status400Exception{

    public InvalidPhoneNumberException() {
        super("잘못된 형식의 전화번호 입니다.");
    }
}
