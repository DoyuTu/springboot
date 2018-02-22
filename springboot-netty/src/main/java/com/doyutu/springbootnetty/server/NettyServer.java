package com.doyutu.springbootnetty.server;

import com.doyutu.springbootnetty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    /**
     * 启动Netty的NIO服务
     * @param port
     *          端口号
     */
    public void server(int port) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

            ChannelFuture future = bootstrap.bind().sync();
            log.warn("服务端运行:{}, port:{}", NettyServer.class.getSimpleName(), future.channel().localAddress());
            future.channel().closeFuture().sync();
        } finally {
            work.shutdownGracefully().sync();
            boss.shutdownGracefully().sync();
        }
    }
}
