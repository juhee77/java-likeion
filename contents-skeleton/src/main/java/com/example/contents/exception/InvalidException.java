package com.example.contents.exception;

public class InvalidException extends Status400Exception {

    public InvalidException() {
        super("잘못된 형식입니다.");
    }
}
