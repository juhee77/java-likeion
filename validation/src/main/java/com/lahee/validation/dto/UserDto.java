package com.lahee.validation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotNull
    private String phone;

    //@Min(14) //null이여도 된다.
    @Min(14) @NotNull //반드시 받아야 하고 14 이상이어야 한다.
    private Integer age;

    @Future// 현재 시점보다 미래여야 한다.
    @NotNull
    private LocalDate localDate;

    @NotNull //널이 아닌지만 검증
    private String notNullString;
    @NotEmpty //길이가 0이 아닌지 검증
    private String notEmptyString;
    @NotBlank // 공백 문자로만 이루어지지는 않았는지 검증
    private String notBlankString;

}
