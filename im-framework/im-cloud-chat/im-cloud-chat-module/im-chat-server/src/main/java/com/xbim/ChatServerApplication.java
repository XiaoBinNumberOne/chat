package com.xbim;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.xbim.common.GlobalConstant;
import com.xbim.protobuf.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobin
 * @date 2020/2/4 19:40
 * @desc
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ChatServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class);
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20880);
        protocolConfig.setName("dubbo");
        return protocolConfig;
    }

    @GetMapping("/test")
    public String test() {
        Message.ChatMessage hello = Message.ChatMessage.newBuilder().setBody("hello").build();
        GlobalConstant.channels.writeAndFlush(hello);
        return "Ok";
    }


}
