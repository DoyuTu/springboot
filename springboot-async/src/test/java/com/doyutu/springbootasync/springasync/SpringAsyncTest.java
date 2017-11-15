package com.doyutu.springbootasync.springasync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAsyncTest{

    private static final Logger log = LoggerFactory.getLogger(SpringAsyncTest.class);

    @Resource
    private SpringAsync springAsync;

    @Test
    public void async() throws Exception {
        springAsync.async();
        log.info("is end!");
        //Junit当执行完当前线程程序时会退出。需要等待异步线程才能查看结果
        Thread.sleep(200L);
    }

}