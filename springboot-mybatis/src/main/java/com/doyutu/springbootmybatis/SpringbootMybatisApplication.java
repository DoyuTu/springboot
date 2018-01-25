package com.doyutu.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author doyutu
 */
@SpringBootApplication
@MapperScan(basePackages = "com.doyutu.springbootmybatis.mapper")
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringbootMybatisApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
