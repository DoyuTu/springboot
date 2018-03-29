package com.doyutu.springbootwebsocket;

import com.doyutu.springbootwebsocket.netty.server.NettyWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootWebsocketApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootWebsocketApplication.class, args);
        NettyWebSocketServer nettyServer = applicationContext.getBean(NettyWebSocketServer.class);
        nettyServer.start();
    }
}
