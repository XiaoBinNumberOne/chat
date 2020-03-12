package com.xbim.websocket.handler;

import com.xbim.common.GlobalConstant;
import com.xbim.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketMsgHandler implements Runnable {


    public WebsocketMsgHandler() {
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Message.ChatMessage take = GlobalConstant.msgQueue.take();
                System.out.println("新消息：" + take.toString());
            } catch (InterruptedException e) {
                log.error("异常InterruptedException,队列take消息失败 e:{}", e);
                e.printStackTrace();
            }
        }
    }
}
