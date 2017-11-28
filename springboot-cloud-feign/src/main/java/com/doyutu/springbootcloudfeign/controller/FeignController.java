package com.doyutu.springbootcloudfeign.controller;

import com.doyutu.springbootcloudfeign.service.FeignServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeignController {

    @Autowired
    private FeignServer feignServer;

    @GetMapping("/feign")
    public String feign() {
        String ribbon = feignServer.consume();
        System.out.println(ribbon);
        return ribbon;
    }

    @GetMapping("/fallback")
    public String fallback() {
        return feignServer.fallback();
    }
}
