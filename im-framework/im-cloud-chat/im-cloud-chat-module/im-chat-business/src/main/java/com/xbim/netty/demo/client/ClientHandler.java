package com.xbim.netty.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * @author xiaobin
 * @date 2020/1/19 23:09
 * @desc
 */
@Log
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        log.info("服务端说：" + s);
    }
}
