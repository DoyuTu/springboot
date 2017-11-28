package com.doyutu.spring.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@EnableAsync
@Component
public class SpringAsync {

    private static final Logger log = LoggerFactory.getLogger(SpringAsync.class);

    @Async
    public Future<String> async() throws InterruptedException {
        Thread.sleep(100L);
        log.info("is async! NOT BLOCKING!");
        return new AsyncResult<>("is over");
    }
}
