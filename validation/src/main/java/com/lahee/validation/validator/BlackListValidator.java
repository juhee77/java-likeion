package com.lahee.validation.validator;

import com.lahee.validation.annotations.Blacklist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BlackListValidator implements ConstraintValidator<Blacklist, String> {
    //사용자 지정 유효성 검사를 위해 구현해야 하는 인터페이스

    private Set<String> blackList;

    @Override //새로 추가
    public void initialize(Blacklist constraintAnnotation) {
        blackList = new HashSet<>();
        Collections.addAll(blackList, constraintAnnotation.blacklist());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !blackList.contains(value); //해당 도메인이 포함되어 있느냐
    }
}
