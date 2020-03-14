package com.xbim.context;

import com.xbim.handle.ClientHandler;
import com.xbim.websocket.handler.NettyWebsocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xiaobin
 * @date 2020/3/14 16:40
 * @desc
 */
@Component
@Slf4j
public class ClientContext implements ApplicationRunner, DisposableBean {


    private ClientHandler clientHandler;

    @Value("${im.chat.websocket.path}")
    private String path;

    public ClientContext() {
        clientHandler = new NettyWebsocketHandler();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("netty client init ...");
        clientHandler.init(path);
        clientHandler.connect();
    }

    @Override
    public void destroy() throws Exception {
        log.info("netty client destory");
        clientHandler.close();
    }
}
