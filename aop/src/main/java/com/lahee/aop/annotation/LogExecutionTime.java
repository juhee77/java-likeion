package com.lahee.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//어디에 붙는 어노테이션인지.
@Retention(RetentionPolicy.RUNTIME) //언제 까지 유지되는지
public @interface LogExecutionTime {

}
