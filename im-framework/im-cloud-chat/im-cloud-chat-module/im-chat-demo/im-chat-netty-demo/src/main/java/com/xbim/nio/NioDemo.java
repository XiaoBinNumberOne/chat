package com.xbim.nio;

import java.nio.IntBuffer;

public class NioDemo {

    public static void main(String[] args) throws Exception {
        // IO核心概念（流）：InputStream和OutputStream，设计模式 装饰模式
        // NIO核心概念：Selector、Channel、buffer，和IO不同，可以同时读/写
        IntBuffer intBuffer = IntBuffer.allocate(100);

        for (int i = 0; i < 10; i++) {
            intBuffer.put(i);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

}
