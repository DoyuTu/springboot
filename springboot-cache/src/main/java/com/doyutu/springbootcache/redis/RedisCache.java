package com.doyutu.springbootcache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "testCache")
public class RedisCache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Autowired
    @Qualifier(value = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    final String WORD = "world hello!";

    public String redis() {
        redisTemplate.opsForValue().set("test", WORD);
        return redisTemplate.opsForValue().get("test");
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
