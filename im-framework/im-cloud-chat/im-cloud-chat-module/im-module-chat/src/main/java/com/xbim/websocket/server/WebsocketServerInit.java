package com.xbim.websocket.server;

import com.xbim.protobuf.Message;
import com.xbim.websocket.encode.MyProtobufDecoder;
import com.xbim.websocket.encode.MyProtobufEncoder;
import com.xbim.websocket.handler.WebsocketServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author xiaobin
 * @date 2020/2/5 19:37
 * @desc 客户端连接后初始化channel
 */
public class WebsocketServerInit extends ChannelInitializer<NioSocketChannel> {

    private String url;

    public WebsocketServerInit(String url) {
        this.url = url;
    }

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // HTTP请求的解码和编码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
        // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(65536));
        // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        // WebSocket数据压缩
        pipeline.addLast("webSocketServerCompressionHandler", new WebSocketServerCompressionHandler());
        pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler(this.url, null, true, 1024 * 10));
        //编码解码
        //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast("myProtobufDecoder", new MyProtobufDecoder());
        pipeline.addLast("myProtobufEncoder", new MyProtobufEncoder());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(Message.ChatMessage.getDefaultInstance()));

        //添加自定义处理器
        pipeline.addLast("websocketServerHandler", new WebsocketServerHandler());

    }

}
