package com.lahee.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //이 클래스가 관점(Aspect)를 드러냄을 나타낸다.
@Component //bean 등록
@Slf4j //log
public class TestAspect {
    // 컨트롤러 클래스
    // @Before: Advice, 실제로 실행될 코드를 나타냄
    // @Before.value: Pointcut Designator, 어느 JoinPoint에서 실행될 것인지

    // this: 클래스 instance 지정
//    @Before(" this(com.lahee.aop.controller.AopController)")
    // within: 클래스 또는 패키지 지정
//    @Before(" within(com.lahee.aop.controller.AopController)")
//    @Before(" within(com.lahee.aop.controller..*)")
    // @annotation: 어노테이션 지정


    @Before(" @annotation(com.lahee.aop.annotation.LogArguments)")
    // JoinPoint: 이 Advice 가 실행된 JoinPoint (addUser)
    public void logParameters(JoinPoint joinPoint) {
        log.info("---------[hello aop!] - 여기부터 AOP에 의해 실행되는 구간입니다.");

        //signature : joinpoint의 정보를 담은 객체
        Signature signature = joinPoint.getSignature();

        //메소드 이름을 확인
        log.info(signature.getName());

        //메소드 인자를 확인한다.
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info("no Args");
        }
        for (Object arg : args) {
            log.info("argument : [{}]", args);
        }
        log.info("---------[bye aop!]");

    }

}
