package com.xbim.nio.demo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author xiaobin
 * @date 2020/2/11 17:08
 * @desc 客户端程序，使用telnet进行测试 主要学习
 */
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel open = ServerSocketChannel.open();

        SocketChannel accept = open.bind(new InetSocketAddress(8899)).accept();
        System.out.println("连接");
        int messageSize = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        while (true) {
            long count = 0;
            while (count < messageSize) {
                long read = accept.read(byteBuffers);
                Arrays.asList(byteBuffers).stream().map((item) -> {
                    return "position:" + item.position() + ",limit:" + item.limit();
                }).forEach(System.out::println);
                count += read;
            }

            // bytebuffer满了，需要写给客户端
            Arrays.asList(byteBuffers).stream().forEach((item) -> {
                item.flip();
            });
            accept.write(byteBuffers);
            Arrays.asList(byteBuffers).stream().forEach((item) -> {
                item.clear();
            });
        }


    }

}
