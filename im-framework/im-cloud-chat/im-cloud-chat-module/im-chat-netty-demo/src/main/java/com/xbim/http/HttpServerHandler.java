package com.xbim.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;
            System.out.println("ip:" + channelHandlerContext.channel().remoteAddress());
            System.out.println("url:" + httpRequest.uri());

            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            channelHandlerContext.channel().writeAndFlush(fullHttpResponse);
            channelHandlerContext.channel().close();
        }
    }
}
