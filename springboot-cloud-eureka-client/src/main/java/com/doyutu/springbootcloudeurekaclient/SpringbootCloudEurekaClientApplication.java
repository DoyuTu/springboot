package com.doyutu.springbootcloudeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootCloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCloudEurekaClientApplication.class, args);
    }
}
