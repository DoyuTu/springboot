package com.doyutu.springbootwebsocket.netty.handler;

import com.doyutu.springbootwebsocket.netty.global.GlobalChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author doyutu
 * @date 2018-03-29 10:43
 * springboot
 */
@ChannelHandler.Sharable
@Slf4j
@Component
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final String WEB_SOCKET_URI = "ws://localhost:8099/ws";

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("IP：{}新用户{}加入",ctx.channel().remoteAddress(), ctx.channel().id());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("IP：{}用户{}离开", ctx.channel().remoteAddress(), ctx.channel().id());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        GlobalChannel.group.add(ctx.channel());
        log.info("与客户端连接建立,共有{}人", atomicInteger.incrementAndGet());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        GlobalChannel.group.remove(ctx.channel());
        log.info("与客户端连接关闭,剩余{}人", atomicInteger.decrementAndGet());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest)msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketRequest(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //关闭
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
        //ping
        if (frame instanceof PingWebSocketFrame) {
            ctx.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //text数据
        if (frame instanceof TextWebSocketFrame) {
            String text = ((TextWebSocketFrame) frame).text();
            log.info("服务端收到信息:{}", text);
            //群发
            GlobalChannel.group.writeAndFlush(new TextWebSocketFrame(System.currentTimeMillis() + ":" + ctx.channel().id() + "," + text));
            //消息应答
            ctx.writeAndFlush(new TextWebSocketFrame("应答数据：" + text + ":" + ctx.channel().id()));
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status() != HttpResponseStatus.OK) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status() != HttpResponseStatus.OK) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static boolean isKeepAlive(FullHttpRequest req) {
        return false;
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URI
                , null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
