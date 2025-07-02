package com.example.demo.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Before("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logBeforeCall(JoinPoint joinPoint) {
		logger.info("Execution started: " + joinPoint.getSignature().getDeclaringTypeName() + " "
				+ joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCall(JoinPoint joinPoint) {
		logger.info("Execution successful: " + joinPoint.getSignature().getDeclaringTypeName() + " "
				+ joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.demo.controller.StudentController.*(..))")
    public void logAfterCallWhenIssue(JoinPoint joinPoint) {
		logger.info("Issue in execution: " + joinPoint.getSignature().getDeclaringTypeName() + " "
				+ joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.demo.controller.StudentController.*(..))")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
		logger.info("Execution time: " + (endTime - startTime) + " ms\n");
        return obj;
    }
}
