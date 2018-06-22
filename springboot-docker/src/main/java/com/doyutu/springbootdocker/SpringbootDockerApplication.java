package com.doyutu.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootDockerApplication {

    @RequestMapping("/")
    public Object index() {
        return "Hello Docker!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerApplication.class, args);
    }
}
