package com.xbim.chat;

import java.nio.channels.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        IntStream.range(0,MAP_SIZE-1).forEach(index->channelMap.put(index,new ConcurrentHashMap<>()));
    }


    @Override
    public void addChannel(String userId, Channel channel) {

    }

    private boolean channelIsExist(String userId){

        return false;
    }

    @Override
    public void closeChannel(String userId) {

    }

    @Override
    public void groupMessage(String message) {

    }

    @Override
    public void singleMessage(String userId, String message) {

    }
}
