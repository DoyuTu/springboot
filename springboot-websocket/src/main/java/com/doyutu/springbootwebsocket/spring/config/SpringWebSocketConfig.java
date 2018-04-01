package com.doyutu.springbootwebsocket.spring.config;

import com.doyutu.springbootwebsocket.spring.handler.SpringWebSocketHandler;
import com.doyutu.springbootwebsocket.spring.shake.SpringWebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author s.z
 * @date 2018-04-01 14:41
 * springboot
 */
@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SpringWebSocketHandshakeInterceptor springWebSocketHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(springWebSocketHandler(), "/ws")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .addInterceptors(springWebSocketHandshakeInterceptor);
    }

    @Bean
    public WebSocketHandler springWebSocketHandler() {
        return new SpringWebSocketHandler();
    }
}
