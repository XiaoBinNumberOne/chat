package com.xbim.websocket.encode;

import com.xbim.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiaobin
 * @date 2020/2/5 19:58
 * @desc
 */
@Slf4j
public class ProtobufDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 标记一下当前的readIndex的位置
        in.markReaderIndex();
        // 判断包头长度
        if (in.readableBytes() < 2) {// 不够包头
            return;
        }
        // 读取传送过来的消息的长度。
        int length = in.readUnsignedShort();
        // 长度如果小于0
        if (length < 0) {// 非法数据，关闭连接
            ctx.close();
        }
        if (length > in.readableBytes()) {// 读到的消息体长度如果小于传送过来的消息长度
            // 重置读取位置
            in.resetReaderIndex();
            return;
        }
        ByteBuf frame = Unpooled.buffer(length);
        in.readBytes(frame);
        try {
            byte[] inByte = frame.array();
            // 字节转成对象
            Message.ChatMessage msg = Message.ChatMessage.parseFrom(inByte);
            if (msg != null) {
                // 获取业务消息头
                out.add(msg);
            }
        } catch (Exception e) {
            log.info(ctx.channel().remoteAddress() + ",decode failed.", e);
        }
    }
}
