package com.lahee.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
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


    //    @After(" @annotation(com.lahee.aop.annotation.LogArguments)")
    @Before(" @annotation(com.lahee.aop.annotation.LogArguments)")
//    @Around(" @annotation(com.lahee.aop.annotation.LogArguments)")
    // JoinPoint: 이 Advice 가 실행된 JoinPoint (addUser)
    public void logParameters(JoinPoint joinPoint) {
        log.info("----[hello LogArguments aop!]");

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
        log.info("----[bye LogArguments aop!]");

    }


    //어떤 메소드가 실행되는데 걸리는 시간을 기록하고 싶다.
    @Around(" @annotation(com.lahee.aop.annotation.LogExecutionTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("---------[hello LogExecutionTime aop!]");
        long startTime = System.currentTimeMillis();

        // joinPoint.proceed() : JoinPoint에 해당하는 메서드를 진행하라는 의미
        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("{} executed in : {}ms", joinPoint.getSignature(), endTime - startTime);
        log.info("---------[bye LogExecutionTime aop!]");
        return proceed;
    }

}
