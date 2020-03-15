package com.xbim.context;

import com.xbim.handle.ClientHandler;
import com.xbim.protobuf.Message;
import com.xbim.websocket.handler.NettyWebsocketHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xiaobin
 * @date 2020/3/14 16:40
 * @desc
 */
@Component
@Slf4j
public class ClientContext implements ApplicationRunner, DisposableBean {

    public static LinkedBlockingQueue<Message.ChatMessage> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

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
        startMsgHandle();

    }

    @Override
    public void destroy() throws Exception {
        log.info("netty client destory");
        clientHandler.close();
    }


    public Channel getChannel() {
        return this.clientHandler.getChannel();
    }

    /**
     * 启动消息监听
     */
    private void startMsgHandle() {
        executorService.execute(new WebsocketMsgHandler());
    }


    class WebsocketMsgHandler implements Runnable {


        public WebsocketMsgHandler() {
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Message.ChatMessage take = linkedBlockingQueue.take();
                    System.out.println("新消息：" + take.toString());
                } catch (InterruptedException e) {
                    log.error("异常InterruptedException,队列take消息失败 e:{}", e);
                    e.printStackTrace();
                }
            }
        }
    }
}
