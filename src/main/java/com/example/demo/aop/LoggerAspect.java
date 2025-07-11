package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Before("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logBeforeCall(JoinPoint joinPoint) {
        log.info("Execution started: {} {}", 
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCall(JoinPoint joinPoint) {
        log.info("Execution successful: {} {}", 
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCallWhenIssue(JoinPoint joinPoint) {
        log.warn("Issue in execution: {} {}", 
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.demo.controller.StudentController.*(..))")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Execution time of {}.{}: {} ms", 
                 proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                 proceedingJoinPoint.getSignature().getName(),
                 (endTime - startTime));
        return obj;
    }
}
