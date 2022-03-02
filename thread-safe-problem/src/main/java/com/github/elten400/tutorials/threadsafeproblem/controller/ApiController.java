package com.github.elten400.tutorials.threadsafeproblem.controller;

import com.github.elten400.tutorials.threadsafeproblem.validation.NumberConstraint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    @GetMapping
    public Integer method(@RequestParam @NumberConstraint Integer num) {
        if (num <= 30) {
            System.out.println("number: " + num);
        }
        return num;
    }


}
