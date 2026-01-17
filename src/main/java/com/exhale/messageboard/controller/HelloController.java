package com.exhale.messageboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//这是一个REST接口，用controller写接口
public class HelloController {
    @GetMapping("/hello")//映射了URL,Spring Boot 内置了Tomcat，启动就能访问
    public String hello(){
        return "hello backend";

    }
}
