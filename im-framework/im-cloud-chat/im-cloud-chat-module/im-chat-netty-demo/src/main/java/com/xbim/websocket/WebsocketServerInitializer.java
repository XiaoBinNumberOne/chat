package com.xbim.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketServerInitializer extends ChannelInitializer {

    public static final String WS_URL = "/ws";

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(8192));

        pipeline.addLast("websocketFrameHandler", new WebsocketFrameHandler());
        pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler(WebsocketServerInitializer.WS_URL));


    }
}
