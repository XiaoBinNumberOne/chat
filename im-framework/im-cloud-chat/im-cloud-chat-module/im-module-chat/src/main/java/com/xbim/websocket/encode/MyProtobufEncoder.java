package com.xbim.websocket.encode;

import com.xbim.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @author xiaobin
 * @date 2020/2/5 19:56
 * @desc
 */
@Slf4j
public class MyProtobufEncoder extends MessageToMessageEncoder<Message.ChatMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message.ChatMessage msg, List<Object> out) throws Exception {
        ByteBuf result = null;
        if (msg instanceof Message.ChatMessage) {
            result = wrappedBuffer(msg.toByteArray());
        }
        // ==== 上面代码片段是拷贝自TCP ProtobufEncoder 源码 ====
        // 然后下面再转成websocket二进制流，因为客户端不能直接解析protobuf编码生成的
        WebSocketFrame frame = new BinaryWebSocketFrame(result);
        out.add(frame);
    }
}
