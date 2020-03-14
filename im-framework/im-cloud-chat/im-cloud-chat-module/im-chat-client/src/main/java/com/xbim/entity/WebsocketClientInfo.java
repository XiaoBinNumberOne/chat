package com.xbim.entity;

import com.xbim.websocket.init.WebsocketNettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author xiaobin
 * @date 2020/3/14 17:00
 * @desc
 */
@Data
@Slf4j
public class WebsocketClientInfo {

    private EventLoopGroup loopGroup;

    private Bootstrap bootstrap;

    private URI websocketURI = null;

    private WebSocketClientHandshaker handshaker;

    private Channel channel;


    public WebsocketClientInfo(String websocketUrl) {

        this.loopGroup = new NioEventLoopGroup(1);
        try {
            this.websocketURI = new URI(websocketUrl);
        } catch (URISyntaxException e) {
            log.error("websocketUrl URISyntaxException.... msg:{}", e);
        }
        this.handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, new DefaultHttpHeaders());
        this.bootstrap = new Bootstrap().option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true).group(loopGroup).channel(NioSocketChannel.class).handler(new WebsocketNettyClientInitializer(handshaker));

    }


    public void connect() {
        try {
            this.channel = bootstrap.connect(new InetSocketAddress(websocketURI.getPort())).sync().channel();
            this.handshaker.handshake(this.channel);
            this.channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("websocket channel connect error... msg:{}", e);
        }
    }

    public void close() {
        loopGroup.shutdownGracefully();
    }

}
