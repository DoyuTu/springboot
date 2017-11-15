package com.doyutu.springbootcache.caffeine;

import org.apache.camel.com.github.benmanes.caffeine.cache.Cache;
import org.apache.camel.com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CaffeineCache {

    public Cache<String, String> caffeineBuild() {
        return Caffeine.newBuilder()
                //在写入1.5秒后过期
                .expireAfterWrite(1500L, TimeUnit.MILLISECONDS)
                .build()
                ;
    }

}
