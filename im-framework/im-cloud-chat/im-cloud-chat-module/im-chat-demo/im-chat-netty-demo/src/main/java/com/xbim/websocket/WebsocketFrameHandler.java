package com.xbim.websocket;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.ObjectUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class WebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private TimedCache<Channel, String> timedCache = CacheUtil.newTimedCache(4000);
    private static DefaultChannelGroup defaultChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!ObjectUtil.isEmpty(msg) && msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            // 进行鉴权
            String token = getToken(fullHttpRequest.uri());
            if (ObjectUtil.isEmpty(token)) {
                ctx.close();
            }
            if (token.equals("xiaobin")) {
                defaultChannelGroup.add(ctx.channel());
                timedCache.put(ctx.channel(), ctx.channel().id().asLongText(), 10000);
                timedCache.schedulePrune(5);
                fullHttpRequest.setUri("/ws");
            } else {
                ctx.close();
            }

        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String aLong = timedCache.get(channelHandlerContext.channel());
        if (ObjectUtil.isEmpty(aLong)) {
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("您好，收到消息【账号信息过期，请重新登录】"));
            channelHandlerContext.close();
        }
        System.out.println("【客户端】:" + textWebSocketFrame.text());
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("您好，收到消息【" + textWebSocketFrame.text() + "】"));
    }


    private String getToken(String url) {
        if (!ObjectUtil.isEmpty(url)) {
            int index = url.indexOf("/", 1);
            if (index != -1) {
                return url.substring(index + 1);
            }
        }
        return null;
    }
}
