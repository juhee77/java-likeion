package com.lahee.validation.dto;

import com.lahee.validation.annotations.EmailWhiteList;
import com.lahee.validation.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    @Size(min = 8, message = "최소 8글자 이상이어야 합니다.")
    private String username;
    @EmailWhiteList //특정도메인만 사용할 수 있도록
    private String email;
    @Phone010
    private String phone;


    //@Min(14) //null이여도 된다.
    @Min(value = 14, message = "14세 미만은 부모님의 동의가 필요합니다.") @NotNull //반드시 받아야 하고 14 이상이어야 한다.
    @Max(value = 2147483647, message = "2147 미만의 숫자를 입력해주세요!")
    private Integer age;

    @Future// 현재 시점보다 미래여야 한다.
    @NotNull
    private LocalDate localDate;

    @NotNull(message = "[MINE]키 값이 있어야 합니다.") //널이 아닌지만 검증
    private String notNullString;
    @NotEmpty(message = "[MINE]길이가 0 이상이어야 합니다.") //길이가 0이 아닌지 검증
    private String notEmptyString;
    @NotBlank(message = "[MINE]공백 문자로만 이루어져서는 안됩니다.") // 공백 문자로만 이루어지지는 않았는지 검증
    private String notBlankString;

}
