package com.xbim.config;

import com.xbim.websocket.handler.WebsocketMsgHandler;
import com.xbim.websocket.server.WebsocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaobin
 * @date 2020/2/5 19:25
 * @desc
 */
@Configuration
@Component
public class SpringBeanLoad implements ApplicationRunner {


    // 启动单例线程
    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @Value("${im.chat.websocket.port}")
    private Integer websocketPort;
    @Value("${im.chat.websocket.url}")
    private String websocketUrl;

    @Bean(initMethod = "initializer", destroyMethod = "closeServer")
    public WebsocketServer getWebsocketServer() {
        return new WebsocketServer(this.websocketPort, this.websocketUrl);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        singleThreadExecutor.execute(new WebsocketMsgHandler());
    }
}
