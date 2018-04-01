package com.doyutu.springbootwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author doyutu
 * @date 2018-04-01
 */
@SpringBootApplication
public class SpringbootWebsocketApplication {

    public static void main(String[] args) {
        //由于端口占用，只
        //【1】 Netty启动方式 端口8099   nettyserver.properties
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootWebsocketApplication.class, args);
//        NettyWebSocketServer nettyServer = applicationContext.getBean(NettyWebSocketServer.class);
//        nettyServer.start();
        //【2】 Spring启动方式 application.properties 端口8099
        SpringApplication.run(SpringbootWebsocketApplication.class, args);
    }
}
