package com.example.contents.exception;

public class UsernameExistException extends Status400Exception {
    public UsernameExistException() {
        super("[ERROR] username not unique");
    }
}
