package com.xbim.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String state = event.state().toString();
            switch (state) {
                case "READER_IDLE":
                    System.out.println("读空闲");
                    break;
                case "WRITER_IDLE":
//                   System.out.println("写空闲");
                    break;
                case "ALL_IDLE":
//                   System.out.println("读/写空闲");
                    break;
            }
        }
    }
}
