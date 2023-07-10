package com.lahee.security.dto;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String username;
    private String password;

}
