package com.xbim.chat;

import java.nio.channels.Channel;

/**
 * @author ningbin
 * @date 2020/1/20 0020 11:52
 * @desc
 */
public interface GlobalChannel {
    /**
     * 增加Channel
     * @param userId
     * @param channel
     */
    void addChannel(String userId,Channel channel);

    /**
     * 关闭Channel
     * @param userId
     */
    void closeChannel(String userId);

    /**
     * 群发信息
     * @param message
     */
    void groupMessage(String message);

    /**
     * 单独信息
     * @param userId 对方ID
     * @param message 消息
     */
    void singleMessage(String userId,String message);
}
