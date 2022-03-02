package com.github.elten400.tutorials.threadsafeproblem.client;


import com.github.elten400.tutorials.threadsafeproblem.validation.NumberConstraint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test-feign-service", url = "http://localhost:8083/api")
public interface ApiFeignClient {

    @GetMapping
    Integer method(@RequestParam @NumberConstraint Integer num);
}
