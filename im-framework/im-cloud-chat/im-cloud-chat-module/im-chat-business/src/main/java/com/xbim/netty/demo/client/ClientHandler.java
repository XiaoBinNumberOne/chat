package com.xbim.netty.demo.client;

import com.xbim.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * @author xiaobin
 * @date 2020/1/19 23:09
 * @desc
 */
@Log
public class ClientHandler extends SimpleChannelInboundHandler<Message.ChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.ChatMessage msg) throws Exception {
        log.info("服务端说：" + msg.getBody());
    }
}
