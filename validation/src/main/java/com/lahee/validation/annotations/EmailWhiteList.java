package com.lahee.validation.annotations;

import com.lahee.validation.validator.EmailWhiteListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //어디에 적용할것인가(선택) , 필드에 적용한다.
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 런타임시점에 직접 사용할 수 있다.
@Constraint(validatedBy = EmailWhiteListValidator.class)
public @interface EmailWhiteList {
    String message() default "email not in whitelit \uD83D\uDE15\uD83D\uDE15\uD83D\uDE15";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
