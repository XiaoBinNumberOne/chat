package com.xbim.chat;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.LogFactory;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author ningbin
 * @date 2020/1/20 0020 11:41
 * @desc
 */
public class GlobalChannelManagement implements GlobalChannel {

    /**
     *  Map 初始化大小
     */
    private static final Integer MAP_SIZE = 128;

    /**
     *  全局Map，Integer(序号)，String(用户ID)，Channel(netty中channel)
     *  好处：提高性能，减少同步锁压力
     */
    private static final Map<Integer,Map<String, Channel>> channelMap = new ConcurrentHashMap<>();

    static {
        IntStream.range(0, MAP_SIZE - 1).forEach(index -> channelMap.put(index, new ConcurrentHashMap<>()));
    }


    @Override
    public void addChannel(String userId, Channel channel) {

    }

    private boolean channelIsExist(String userId) {
        Channel channel = searchChannel(userId);
        return Optional.ofNullable(ObjectUtil.isEmpty(channel)).orElse(true);
    }

    /**
     * 通过ID查询channel
     *
     * @param userId
     * @return
     */
    private Channel searchChannel(String userId) {
        if (!ObjectUtil.isEmpty(userId)) {
            for (Map.Entry<Integer, Map<String, Channel>> entry : channelMap.entrySet()) {
                for (Map.Entry<String, Channel> channelEntry : entry.getValue().entrySet()) {
                    if (userId.equals(channelEntry.getKey())) {
                        return channelEntry.getValue();
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void closeChannel(String userId) {
        Channel channel = searchChannel(userId);
        if (!ObjectUtil.isEmpty(channel)) {
            try {
                channel.close();
            } catch (IOException e) {
                LogFactory.get().error("关闭channel错误，信息{}", e);
            }
        }
    }

    @Override
    public void groupMessage(String message) {

    }

    @Override
    public void singleMessage(String userId, String message) {

    }
}
