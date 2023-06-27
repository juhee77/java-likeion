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
    // @Before  : advice tlfwpfh tlfgodehlf zhemfmf skxksoa
    // @Before.value : Pointcut Designator, 어느 JoinPoint에서 실행될것인지
    @Before(" this(com.lahee.aop.controller.AopController)")
    //joinpoint :
    //execution :
    public void logParameters(JoinPoint joinPoint) {
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
        log.info("hello aop!");
    }

}
