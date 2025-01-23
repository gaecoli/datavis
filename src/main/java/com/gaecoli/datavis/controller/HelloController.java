package com.gaecoli.datavis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final String hello = "Hello, World!";

    @GetMapping(value = "/hello")
    public String hello() {
        return hello;
    }
}
