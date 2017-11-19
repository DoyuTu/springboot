package com.doyutu.springbootcloudfeign.hystrix;

import com.doyutu.springbootcloudfeign.service.FeignServer;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallBack implements FallbackFactory<FeignServer> {

    @Override
    public FeignServer create(Throwable e) {
        System.out.println(e.getMessage());
        return new FeignServer() {
            @Override
            public String consume() {
                return "consume fallback";
            }

            @Override
            public String fallback() {
                return "fallback";
            }
        };
    }
}
