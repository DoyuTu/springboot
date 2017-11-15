package com.doyutu.springbootcache.springcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@EnableCaching
@Component
public class SpringCache {
    private static final Logger log = LoggerFactory.getLogger(SpringCache.class);

    @Cacheable("keys")
    public List<String> cacheable() {
        //缓存之后会忽略 log 输出
        log.warn("not cache");
        return Arrays.asList("cache 1", "cache 2");
    }
}
