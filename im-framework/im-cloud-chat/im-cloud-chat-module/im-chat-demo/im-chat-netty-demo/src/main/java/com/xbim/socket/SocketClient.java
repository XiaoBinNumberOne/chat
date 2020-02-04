package com.xbim.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(workGroup).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).handler(new SocketClientInit());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 10010).sync();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channelFuture.channel().writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } finally {
            workGroup.shutdownGracefully();
        }
    }

}
