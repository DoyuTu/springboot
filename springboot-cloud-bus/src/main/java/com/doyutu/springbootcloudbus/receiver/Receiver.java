package com.doyutu.springbootcloudbus.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "hello")
    public void run(String hello) {
        logger.info("Receiver:" + hello);
    }

}
