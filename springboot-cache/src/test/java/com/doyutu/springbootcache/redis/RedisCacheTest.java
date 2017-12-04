package com.doyutu.springbootcache.redis;

import com.doyutu.springbootcache.BaseTester;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisCacheTest extends BaseTester {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void redis() throws Exception {
        Assert.assertEquals(redisCache.redis(), "world hello!");
    }

}