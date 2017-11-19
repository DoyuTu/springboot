package com.doyutu.springbootcloudfeign.service;

import com.doyutu.springbootcloudfeign.hystrix.HystrixClientFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eureka-client", fallbackFactory = HystrixClientFallBack.class)
@Primary
public interface FeignServer {

    @RequestMapping(value = "/dc", method = RequestMethod.GET)
    String consume();

    @RequestMapping(value = "/fallback", method = RequestMethod.GET)
    String fallback();

}
