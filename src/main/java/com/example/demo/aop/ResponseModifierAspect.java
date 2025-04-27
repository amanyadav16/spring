package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ResponseModifierAspect {

    @Around("execution(* com.example.demo.controller.StudentController.*(..))")
    public Object modifyResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof ResponseEntity<?> responseEntity) {
            Map<String, Object> response = new HashMap<>();
            response.put("time", LocalDateTime.now());
            response.put("status", responseEntity.getStatusCode().value());
            response.put("data", responseEntity.getBody());
            return new ResponseEntity<>(response, responseEntity.getStatusCode());
        }

        return result;
    }
}