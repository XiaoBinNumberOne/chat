package com.xbim.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.java.Log;

/**
 * @author xiaobin
 * @date 2020/1/19 20:29
 * @desc
 */
@Log
public final class NettyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, wokerGroup)
                    //设置要被实例化的为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 设置连入服务端的 Client 的 SocketChannel 的处理器
                    .childHandler(new ServerInit());
            ChannelFuture channelFuture = serverBootstrap.bind(9999);
            log.info("端口：" + 9999 + ",启动完毕");
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭两个 EventLoopGroup 对象
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }

}
