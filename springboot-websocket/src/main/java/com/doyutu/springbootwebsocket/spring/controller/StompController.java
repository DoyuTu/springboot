package com.doyutu.springbootwebsocket.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author s.z
 * @date 2018-04-02 00:18
 * springboot
 */
@RestController
@Slf4j
public class StompController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 应答消息
     *
     * @param message
     * @return
     */
    @MessageMapping("/getStomp")
    @SendTo("/topic/helloworld")
    public String getStomp(String message) {
        log.info("接收信息：{}", message);
        return message;
    }

    /**
     * 自定义发送消息
     */
    @GetMapping("/get")
    public void get() {
        template.convertAndSend("/topic/helloworld", "payloadddd");
    }
}
