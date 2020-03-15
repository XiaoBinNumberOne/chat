package com.xbim.common;

import com.xbim.protobuf.Message;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xiaobin
 * @date 2020/2/5 19:41
 * @desc 全局常量设置
 */
public interface GlobalConstant {


    public static final LinkedBlockingDeque<Message.ChatMessage> msgQueue = new LinkedBlockingDeque<>();

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


}
