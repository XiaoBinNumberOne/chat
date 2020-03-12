package com.xbim.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebsocketNettyClient {


    public static void main(String[] args) throws Exception {

        NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);

        URI websocketURI = new URI("ws://localhost:10010/ws/xiaobin");
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, new DefaultHttpHeaders());

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            System.out.println("channelRead0  " + handshaker.isHandshakeComplete());
        }, 5, TimeUnit.SECONDS);

        Bootstrap bootstrap = new Bootstrap().option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true).group(loopGroup).channel(NioSocketChannel.class).handler(new WebsocketNettyClientInitializer(handshaker));
        Channel channel = bootstrap.connect(new InetSocketAddress(10010)).sync().channel();
        handshaker.handshake(channel);
        channel.closeFuture().sync();


    }

}
