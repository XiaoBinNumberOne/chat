package com.xbim.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xiaobin
 * @date 2020/1/19 20:44
 * @desc
 */
public class ServerInit extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 添加帧限定符来防止粘包现象
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 解码和编码，应和客户端一致
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);
        // 业务逻辑实现类
        pipeline.addLast(new ServerHandler());
    }
}
