package com.doyutu.springbootcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringbootCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCloudConfigServerApplication.class, args);
	}
}
