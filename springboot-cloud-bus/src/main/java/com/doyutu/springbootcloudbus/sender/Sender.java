package com.doyutu.springbootcloudbus.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    void send() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
