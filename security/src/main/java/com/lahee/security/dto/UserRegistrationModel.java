package com.lahee.security.dto;

import lombok.Data;

@Data
public class UserRegistrationModel {
    private String username;
    private String password;
    private String passwordCheck;

    // 생성자, getter, setter 생략

    // 추가적인 필요한 메서드 구현 가능
}
