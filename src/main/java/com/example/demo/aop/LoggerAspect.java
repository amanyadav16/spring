package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    //returnType fullyQualifiedClassName.method(arguments)
    @Before("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logBeforeCall(JoinPoint joinPoint) {
        //executionTime = 0;
        log.info("Execution started: {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCall(JoinPoint joinPoint) {
        log.info("Execution successful: {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCallWhenIssue(JoinPoint joinPoint) {
        log.info("Issue in execution: {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.demo.controller.StudentController.*(..))")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Execution time :{} ms\n",(endTime-startTime));
        return obj;
    }
}
