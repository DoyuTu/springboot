package com.doyutu.springbootwebsocket;

import com.doyutu.springbootwebsocket.netty.server.NettyWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author doyutu
 * @date 2018-04-01
 */
@SpringBootApplication
public class SpringbootWebsocketApplication {

    public static void main(String[] args) throws Exception {
        //【1】 Netty启动方式 端口8099   nettyserver.properties
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootWebsocketApplication.class, args);
        NettyWebSocketServer nettyServer = applicationContext.getBean(NettyWebSocketServer.class);
        nettyServer.start();
        //【2】 Spring启动方式 application.properties
        SpringApplication.run(SpringbootWebsocketApplication.class, args);
    }
}
