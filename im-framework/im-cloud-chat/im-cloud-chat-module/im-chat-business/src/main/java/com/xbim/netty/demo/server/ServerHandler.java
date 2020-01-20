package com.xbim.netty.demo.server;

import com.xbim.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author xiaobin
 * @date 2020/1/19 20:49
 * @desc
 */
@Log
public class ServerHandler extends SimpleChannelInboundHandler<Message.ChatMessage> {
    private static LongAdder longAdder = new LongAdder();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        longAdder.increment();
        log.info("新连接加入,当前人数：" + longAdder.intValue());
        ctx.channel().writeAndFlush( Message.ChatMessage.newBuilder().setBody("欢迎您加入聊天室"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.ChatMessage message) throws Exception {
        log.info("服务端收到消息：" + message.getBody());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning("出现异常");
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        longAdder.decrement();
        log.info("新连接退出,当前人数：" + longAdder.intValue());
        ctx.channel().close();
    }
}
