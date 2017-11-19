package com.doyutu.springbootcloudfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringbootCloudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCloudFeignApplication.class, args);
	}
}
