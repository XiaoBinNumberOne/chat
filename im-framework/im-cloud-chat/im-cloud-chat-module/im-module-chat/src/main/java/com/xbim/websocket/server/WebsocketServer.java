package com.xbim.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaobin
 * @date 2020/2/5 19:12
 * @desc netty启动类
 */
@Slf4j
public class WebsocketServer {

    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;
    private ServerBootstrap serverBootstrap;

    private Integer port;
    private String wsUrl;

    public WebsocketServer(Integer port, String wsUrl) {
        this.port = port;
        this.wsUrl = wsUrl;
    }

    public void initializer() {
        this.parentGroup = new NioEventLoopGroup(1);
        this.childGroup = new NioEventLoopGroup();
        this.serverStart();
    }

    public void serverStart() {
        serverBootstrap = new ServerBootstrap().group(parentGroup, childGroup)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class).childHandler(new WebsocketServerInit(this.wsUrl));
        try {
            ChannelFuture sync = serverBootstrap.bind(this.port).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("启动netty错误信息:{}", e);
        }
    }

    public void closeServer() {
        this.parentGroup.shutdownGracefully();
        this.childGroup.shutdownGracefully();
    }
}
