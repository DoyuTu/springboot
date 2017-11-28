package com.doyutu.springbootcloudhystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Primary
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String getService(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }

    public String fallback(String url) {
        System.out.println(url + "：Ribbon异常回调");
        return url;
    }

}
