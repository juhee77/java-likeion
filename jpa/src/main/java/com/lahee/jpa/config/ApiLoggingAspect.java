package com.lahee.jpa.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logApiRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get the class and method name
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        // Get the request parameter
        Object[] args = joinPoint.getArgs();

        // Log the API request
        logger.info("API Request - {}.{} - Request: {}", className, methodName, args);

        // Proceed with the API call
        Object result = joinPoint.proceed();

        // Log the API response
        logger.info("API Response - {}.{} - Response: {}", className, methodName, result);

        return result;
    }
}
