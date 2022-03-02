package com.github.elten400.tutorials.threadsafeproblem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ThreadSafeProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadSafeProblemApplication.class, args);
    }

}
