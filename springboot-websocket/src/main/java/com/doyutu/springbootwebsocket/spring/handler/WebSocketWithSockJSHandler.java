package com.doyutu.springbootwebsocket.spring.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author s.z
 * @date 2018-04-01 20:36
 * springboot
 */
@Slf4j
public class WebSocketWithSockJSHandler extends TextWebSocketHandler {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("{}加入主机，共有{}人", session.getId(), atomicInteger.incrementAndGet());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("收到来自{}的消息：{}", session.getId(), message.getPayload());
        //应答消息。广播 @see SpringWebSocketHandler
        session.sendMessage(new TextMessage("应答用户" + session.getId() + "的消息:" + message.getPayload()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("{}连接异常", session.getId());
        exception.printStackTrace();
        session.close();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{}退出，方式：{}，剩余：{}人", session.getId(), status.getCode(), atomicInteger.decrementAndGet());
        session.close();
    }
}
