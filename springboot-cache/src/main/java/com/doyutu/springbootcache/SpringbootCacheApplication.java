package com.doyutu.springbootcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@Configuration
public class SpringbootCacheApplication {

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }
}
