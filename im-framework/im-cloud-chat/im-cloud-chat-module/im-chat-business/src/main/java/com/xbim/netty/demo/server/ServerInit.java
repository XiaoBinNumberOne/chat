package com.xbim.netty.demo.server;

import com.xbim.protobuf.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xiaobin
 * @date 2020/1/19 20:44
 * @desc
 */
public class ServerInit extends ChannelInitializer<SocketChannel> {

    private static final ProtobufDecoder DECODER = new ProtobufDecoder(Message.ChatMessage.getDefaultInstance());
    private static final ProtobufEncoder ENCODER = new ProtobufEncoder();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //拆包解码（重要:发送和接收byte长度不统一）
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        // 使用Protobuf
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);

        // 业务逻辑实现类
        pipeline.addLast(new ServerHandler());
    }
}
