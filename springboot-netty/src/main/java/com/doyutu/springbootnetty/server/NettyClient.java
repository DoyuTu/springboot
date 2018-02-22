package com.doyutu.springbootnetty.server;


import com.doyutu.springbootnetty.handler.Clienthandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {

    private final String address;

    private final int  port;

    public NettyClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * 启动Netty的NIO客户端
     */
    public void client() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(address, port)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new Clienthandler()
                        );
                    }
                });

            ChannelFuture future = b.connect().sync();

            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
