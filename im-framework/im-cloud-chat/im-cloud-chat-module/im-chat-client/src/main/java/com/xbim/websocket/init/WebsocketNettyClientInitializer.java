package com.xbim.websocket.init;

import com.xbim.protobuf.Message;
import com.xbim.websocket.encode.MyProtobufDecoder;
import com.xbim.websocket.encode.MyProtobufEncoder;
import com.xbim.websocket.handler.WebSocketClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketNettyClientInitializer extends ChannelInitializer<SocketChannel> {

    private WebSocketClientHandshaker handshaker;

    public WebsocketNettyClientInitializer(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // HTTP请求的解码和编码
        // HTTP请求的解码和编码
        pipeline.addLast("httpServerCodec", new HttpClientCodec());
        // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
        // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(65536));
        // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        // WebSocket数据压缩
        pipeline.addLast("webSocketServerCompressionHandler", new WebSocketServerCompressionHandler());

        // 编码解码
        // 解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast("myProtobufDecoder", new MyProtobufDecoder());
        pipeline.addLast("myProtobufEncoder", new MyProtobufEncoder());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(Message.ChatMessage.getDefaultInstance()));
        pipeline.addLast("hookedHandler", new WebSocketClientHandler(handshaker, socketChannel.newPromise()));

    }
}
