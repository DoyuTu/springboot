package com.doyutu.springbootcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringbootCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCloudZuulApplication.class, args);
	}
}
