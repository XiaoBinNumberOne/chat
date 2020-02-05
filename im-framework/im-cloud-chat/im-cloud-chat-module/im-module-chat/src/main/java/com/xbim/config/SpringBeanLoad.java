package com.xbim.config;

import com.xbim.websocket.server.WebsocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaobin
 * @date 2020/2/5 19:25
 * @desc
 */
@Configuration
public class SpringBeanLoad {


    @Value("${im.chat.websocket.port}")
    private Integer websocketPort;
    @Value("${im.chat.websocket.url}")
    private String websocketUrl;

    @Bean(initMethod = "initializer", destroyMethod = "closeServer")
    public WebsocketServer getWebsocketServer() {
        return new WebsocketServer(this.websocketPort, this.websocketUrl);
    }

}
