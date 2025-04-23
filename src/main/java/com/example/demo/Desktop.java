package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer{
    @Override
    public void performTask() {
        System.out.println("Desktop performing task...");
    }
}
