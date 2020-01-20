package com.xbim.netty.demo.client;

import com.xbim.protobuf.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author xiaobin
 * @date 2020/1/19 22:55
 * @desc
 */
@Log
public class NettyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInit());

            Channel channel = bootstrap.connect("127.0.0.1", 9999).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
//                String s = in.readLine();
                channel.writeAndFlush(Message.ChatMessage.newBuilder().setBody("hello"));

            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
