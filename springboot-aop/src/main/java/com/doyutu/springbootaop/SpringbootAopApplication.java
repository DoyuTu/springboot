package com.doyutu.springbootaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class SpringbootAopApplication {

    @Bean
    public ServletContextInitializer initializer() {
        return servletContext -> servletContext.setInitParameter("scanPath", "com.doyutu.springbootaop");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopApplication.class, args);
    }
}
