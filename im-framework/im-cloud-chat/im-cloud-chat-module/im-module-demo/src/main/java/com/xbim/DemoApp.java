package com.xbim;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.xbim.chat.service.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobin
 * @date 2020/2/10 13:50
 * @desc
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xbim.chat.service")
@RestController
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class);
    }

    @Autowired
    private TestFeign testFeign;

    @GetMapping("/hello")
    public String hello() {
        String test = testFeign.test();
        System.out.println(test);
        return "hello";
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20880);
        protocolConfig.setName("dubbo");
        return protocolConfig;
    }

}
