package com.doyutu;

import com.doyutu.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base {

    @RequestMapping("/index.htm")
    public String index() {
        return "index";
    }

    @RequestMapping("/t")
    public void t(){
        throw new MyException("异常啦");
    }
}
