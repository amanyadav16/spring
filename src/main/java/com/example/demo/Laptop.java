package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Laptop implements Computer {

    @Override
    public void performTask() {
        System.out.println("Laptop performing task...");
    }

    @PostConstruct
    public void runBefore(){
        System.out.println("Running before Laptop Initialised");
    }

    @PreDestroy
    public void runAfter(){
        System.out.println("Running after Laptop Initialised");
    }
}
