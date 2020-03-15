package com.xbim.handle;

import io.netty.channel.Channel;

/**
 * @author xiaobin
 * @date 2020/3/14 16:55
 * @desc
 */
public interface ClientHandler {


    /**
     * 初始化连接
     */
    void init(String url);


    /**
     * 进行连接
     */
    void connect();


    /**
     * 关闭连接
     */
    void close();


    Channel getChannel();


}
