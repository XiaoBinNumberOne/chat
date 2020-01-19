package com.xbim.server;

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
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static LongAdder longAdder = new LongAdder();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        longAdder.increment();
        log.info("新连接加入,当前人数：" + longAdder.intValue());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        log.info("长ID:" + channelHandlerContext.channel().id().asLongText() + ",短ID：" + channelHandlerContext.channel().id().asShortText());
        log.info("服务端收到消息：" + message);
        channelHandlerContext.channel().writeAndFlush("欢迎加入im");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning("出现异常");
        cause.printStackTrace();
        ctx.channel().closeFuture();
    }
}
