package com.example.contents.exception;

public abstract class Status400Exception extends RuntimeException {
    /**
     * 해당 에러(400에러)가 발생 할 수 있는 상황들
     * 1. username 중복
     * 2. email 오류
     * 3. 전화번호 오류
     * 4. 자기소개 너무 긴 경우
     */

    public Status400Exception(String message) {
        super(message);
    }

}
