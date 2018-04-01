package com.doyutu.springbootwebsocket.spring.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author s.z
 * @date 2018-04-01 14:35
 * springboot
 */
@Slf4j
public class SpringWebSocketHandler extends AbstractWebSocketHandler {

    private static ConcurrentHashMap<String, WebSocketSession> map = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("客户ID：{}加入主机", session.getId());
        map.put(session.getId(), session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.warn("客户ID：{}处理异常", session.getId());
        exception.printStackTrace();
        map.remove(session.getId());
        session.close();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("客户ID：{}离线， 方式{}", session.getId(), status.getReason());
        map.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("接收客户端消息：{}", message.getPayload());
        //应答
        session.sendMessage(new TextMessage("服务器应答消息：" + message.getPayload()));
        //群发
        WebSocketSession userSession;
        for (String s : map.keySet()) {
            userSession = map.get(s);
            if (map.get(s).isOpen()) {
                userSession.sendMessage(new TextMessage("群发消息：" + message.getPayload()));
            }
        }
    }

}
