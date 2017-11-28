package com.doyutu.springbootasync.springasync;

import com.doyutu.spring.async.SpringAsync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAsyncTest {

    private static final Logger log = LoggerFactory.getLogger(SpringAsyncTest.class);

    @Resource
    private SpringAsync springAsync;

    @Test
    public void async() throws Exception {
        Future<String> future = springAsync.async();
        log.info("is end!");
        //Junit当执行完当前线程程序时会退出。需要等待异步线程才能查看结果
        while (true) {
            if (future.isDone()) {
                log.info(future.get());
                break;
            }
        }
    }
}