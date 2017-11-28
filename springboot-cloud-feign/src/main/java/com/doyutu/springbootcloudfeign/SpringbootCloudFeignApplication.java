package com.doyutu.springbootcloudfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 开启 Dashboard必须使用 @EnableCircuitBreaker 来支持hystrix.stream。
 * http://localhost:34001/hystrix.stream API Stream
 * http://localhost:34001/hystrix Dashboard 仪表盘
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
public class SpringbootCloudFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCloudFeignApplication.class, args);
    }
}
