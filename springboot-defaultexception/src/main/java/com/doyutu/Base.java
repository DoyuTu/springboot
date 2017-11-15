package com.doyutu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base {

    @RequestMapping("/index.htm")
    public String index() {
        return "index";
    }
}
