package com.xbim.websocket.handler;

import com.xbim.handle.ClientHandler;
import com.xbim.entity.WebsocketClientInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaobin
 * @date 2020/3/14 17:57
 * @desc
 */
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
            this.websocketClientInfo.connect();
        });
    }

    @Override
    public void close() {
        this.websocketClientInfo.close();
    }

    public WebsocketClientInfo getWebsocketClientInfo() {
        return this.websocketClientInfo;
    }
}
