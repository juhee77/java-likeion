package com.lahee.validation.validator;

import com.lahee.validation.annotations.EmailWhiteList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class EmailWhiteListValidator implements ConstraintValidator<EmailWhiteList, String> {
    //사용자 지정 유효성 검사를 위해 구현해야 하는 인터페이스

    private final Set<String> whiteList;

    public EmailWhiteListValidator() {
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //유효한 값일때 true
        //유요하지 않으면 false
        String[] split = value.split("@");
        String domain = split[split.length - 1];

        return whiteList.contains(domain); //해당 도메인이 포함되어 있느냐
    }
}
