package com.doyutu.springbootcache.redis;

import com.doyutu.springbootcache.BaseTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class RedisCacheTest extends BaseTester {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void redis() throws Exception {
        assertEquals(redisCache.redis(), redisCache.WORD);
    }

}