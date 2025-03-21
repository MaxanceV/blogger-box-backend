package com.dauphine.bloggerboxbackend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    //http://localhost:1234/Hello?name=maxance
    @GetMapping("hello")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name;
    }

    // http://localhost:1234/hello/maxance
    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello " + name;
    }

}
