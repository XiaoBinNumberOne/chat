package com.xbim.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.atomic.LongAdder;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private LongAdder longAdder = new LongAdder();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String state = event.state().toString();
            switch (state) {
                case "READER_IDLE":
                    System.out.println("读空闲");
                    longAdder.increment();
                    // 如果读空闲超过3次，关闭channel
                    if (longAdder.intValue() > 3) {
                        ctx.close();
                    }
                    break;
                case "WRITER_IDLE":
                    System.out.println("写空闲");
                    break;
                case "ALL_IDLE":
//                   System.out.println("读/写空闲");
                    break;
            }
        }
    }
}
