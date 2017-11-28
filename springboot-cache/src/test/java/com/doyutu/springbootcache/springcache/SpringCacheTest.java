package com.doyutu.springbootcache.springcache;

import com.doyutu.springbootcache.BaseTester;
import com.doyutu.springbootcache.caffeine.CaffeineCache;
import org.apache.camel.com.github.benmanes.caffeine.cache.Cache;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class SpringCacheTest extends BaseTester {

    private static final Logger log = LoggerFactory.getLogger(SpringCacheTest.class);

    @Resource
    private SpringCache springCache;

    @Test
    public void springCacheTest() throws Exception {
        springCache.cacheable();
        springCache.cacheable();
    }

    @Resource
    private CaffeineCache caffeineCache;

    @Test
    public void caffeineTest() throws InterruptedException {
        Cache<String, String> cache = caffeineCache.caffeineBuild();
        cache.put("A", "1");
        Assert.assertNotNull(cache.getIfPresent("A"));
        Thread.sleep(1500L);
        Assert.assertNull(cache.getIfPresent("A"));
    }


}