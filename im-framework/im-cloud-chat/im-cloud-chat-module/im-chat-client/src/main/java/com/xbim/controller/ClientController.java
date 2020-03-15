package com.xbim.controller;

import com.xbim.context.ClientContext;
import com.xbim.protobuf.Message;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobin
 * @date 2020/3/15 19:12
 * @desc
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientContext clientContext;

    @GetMapping("/send/msg")
    public String clientSendMsg() {
        Channel channel = clientContext.getChannel();
        Message.ChatMessage hello = Message.ChatMessage.newBuilder().setBody("hello").build();
        channel.writeAndFlush(hello);
        return "ok";
    }

}
