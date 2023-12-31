package com.lahee.validation.annotations;

import com.lahee.validation.validator.BlackListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BlackListValidator.class)
public @interface Blacklist {
    String message() default "username in blacklist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //추가 Element 작성
    String[] blacklist() default {};
}