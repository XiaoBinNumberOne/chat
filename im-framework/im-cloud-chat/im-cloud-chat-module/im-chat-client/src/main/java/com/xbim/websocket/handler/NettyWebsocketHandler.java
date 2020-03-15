package com.xbim.websocket.handler;

import com.xbim.handle.ClientHandler;
import com.xbim.entity.WebsocketClientInfo;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaobin
 * @date 2020/3/14 17:57
 * @desc
 */
@Slf4j
public class NettyWebsocketHandler implements ClientHandler {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private WebsocketClientInfo websocketClientInfo;

    private String websocketUrl;


    @Override
    public void init(String websocketUrl) {
        this.websocketUrl = websocketUrl;
        this.websocketClientInfo = new WebsocketClientInfo(this.websocketUrl);
    }

    @Override
    public void connect() {
        executorService.execute(() -> {
            log.info("client connect ...");
            this.websocketClientInfo.connect();
        });
    }

    @Override
    public void close() {
        this.websocketClientInfo.close();
    }

    @Override
    public Channel getChannel() {
        return websocketClientInfo.getChannel();
    }

    public WebsocketClientInfo getWebsocketClientInfo() {
        return this.websocketClientInfo;
    }
}
