package com.doyutu.springbootwebsocket.netty.global;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author doyutu
 * @date 2018-03-29 10:59
 * springboot
 */
public class GlobalChannel {

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
