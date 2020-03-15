package com.xbim.websocket.handler;

import com.xbim.common.GlobalConstant;
import com.xbim.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaobin
 * @date 2020/2/5 20:02
 * @desc
 */
@Slf4j
public class WebsocketServerHandler extends SimpleChannelInboundHandler<Message.ChatMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.ChatMessage msg) throws Exception {
        GlobalConstant.msgQueue.put(msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        GlobalConstant.channels.add(ctx.channel());
        super.handlerAdded(ctx);
    }
}
