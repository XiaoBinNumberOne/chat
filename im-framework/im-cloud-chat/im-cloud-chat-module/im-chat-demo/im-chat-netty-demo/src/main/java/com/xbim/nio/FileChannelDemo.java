package com.xbim.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiaobin
 * @date 2020/2/11 13:47
 * @desc
 */
public class FileChannelDemo {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\file.txt"));
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channel.read(byteBuffer);
        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            System.out.print(" " + (char) byteBuffer.get());
        }
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\file.txt"));
        FileChannel channel1 = fileOutputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(512);
        byte[] bytes = "hello java nio".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            allocate.put(bytes[i]);
        }
        allocate.flip();
        channel1.write(allocate);
        fileOutputStream.close();
    }
}
