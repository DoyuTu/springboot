package com.doyutu.springbootwebsocket.spring.config;

import com.doyutu.springbootwebsocket.spring.handler.SpringWebSocketHandler;
import com.doyutu.springbootwebsocket.spring.handler.WebSocketWithSockJSHandler;
import com.doyutu.springbootwebsocket.spring.shake.SpringWebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author s.z
 * @date 2018-04-01 14:41
 * springboot
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class SpringWebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    @Autowired
    private SpringWebSocketHandshakeInterceptor springWebSocketHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //WebSocket 推荐优先使用
        registry.addHandler(springWebSocketHandler(), "/ws")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .addInterceptors(springWebSocketHandshakeInterceptor);

        //SockJs 轮询方式，兼容WebSocket
        registry.addHandler(webSocketWithSockJSHandler(), "/sockjs")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .addInterceptors(springWebSocketHandshakeInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * WebSocket STOMP方式
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册 STOMP 端点
        registry.addEndpoint("/stomp")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //接收STOMP信息前缀
        registry.enableSimpleBroker("/topic", "/queue");
        //发送STOMP信息前缀
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Bean
    public WebSocketHandler springWebSocketHandler() {
        return new SpringWebSocketHandler();
    }

    @Bean
    public WebSocketWithSockJSHandler webSocketWithSockJSHandler() {
        return new WebSocketWithSockJSHandler();
    }
}
