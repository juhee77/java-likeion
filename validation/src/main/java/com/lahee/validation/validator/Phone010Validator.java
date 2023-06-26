package com.lahee.validation.validator;

import com.lahee.validation.annotations.Phone010;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Phone010Validator implements ConstraintValidator<Phone010, String> {
    //사용자 지정 유효성 검사를 위해 구현해야 하는 인터페이스

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //유효한 값일때 true
        //유요하지 않으면 false

//        if (value.substring(0, 3).equals("010")) return true;
//        return false;

//        return value.startsWith("(010)") || value.startsWith("010-");
        return value.matches("^(\\(010\\) |010-)\\d{3,4}-\\d{4}$");
    }
}
