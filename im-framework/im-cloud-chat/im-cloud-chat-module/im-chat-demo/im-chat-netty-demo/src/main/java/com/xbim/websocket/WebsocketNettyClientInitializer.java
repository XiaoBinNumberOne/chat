package com.xbim.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;

public class WebsocketNettyClientInitializer extends ChannelInitializer<SocketChannel> {

    private WebSocketClientHandshaker handshaker;

    public WebsocketNettyClientInitializer(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new ChannelHandler[]{new HttpClientCodec(),
                new HttpObjectAggregator(1024 * 1024 * 10)});
        p.addLast("hookedHandler", new WebSocketClientHandler(handshaker, socketChannel.newPromise()));
    }
}
