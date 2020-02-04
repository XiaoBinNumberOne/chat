package com.xbim.socket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class SocketClientHandler extends SimpleChannelInboundHandler<String> {

    private static Map<String, Future> map = new HashMap<>();
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端加入");
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
            ctx.channel().writeAndFlush("ping");
        }, 1, 14, TimeUnit.SECONDS);
        map.put(ctx.channel().id().asLongText(), scheduledFuture);
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端移除");
        Future future = map.get((ctx.channel().id().asLongText()));
        future.cancel(true);
        super.handlerRemoved(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("【服务端】:" + s);
    }


}
