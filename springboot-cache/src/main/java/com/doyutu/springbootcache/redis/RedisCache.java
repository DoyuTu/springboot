package com.doyutu.springbootcache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "testCache")
public class RedisCache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String redis() {
        redisTemplate.opsForValue().set("test", "world hello!");
        return redisTemplate.opsForValue().get("test").toString();
    }

    /**
     * 将按照
     *
     * @return
     */
    @Cacheable
    public String text() {
        System.out.println("text");
        return "text";
    }

}
