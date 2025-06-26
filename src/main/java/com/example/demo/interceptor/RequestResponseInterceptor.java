package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestResponseInterceptor implements HandlerInterceptor {
    // Before controller method
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("🚀 PreHandle: " + request.getMethod() + " " + request.getRequestURI());
        return true; // true means continue to controller
    }

    // After controller method, before view rendering
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("📦 PostHandle: Response Status = " + response.getStatus());
    }

    // After complete request
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("✅ AfterCompletion: Request Completed");
    }
}
