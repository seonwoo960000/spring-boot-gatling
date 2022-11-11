package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {

    @GetMapping("/test1")
    public String test1() throws InterruptedException {
        System.out.println("test1");
        Thread.sleep(1000);
        return "test1";
    }

    @GetMapping("/test2/{param}")
    public String test2(@PathVariable String param) throws InterruptedException {
        System.out.println(param);
        Thread.sleep(1000);
        return param;
    }

}
