package com.doyutu.springbootasync.springasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class SpringAsync {

    private static final Logger log = LoggerFactory.getLogger(SpringAsync.class );

    @Async
    public void async() throws InterruptedException {
        Thread.sleep(200L);
        log.info("is async! NOT BLOCKING!");
    }
}
