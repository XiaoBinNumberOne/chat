package com.xbim.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiaobin
 * @date 2020/2/11 16:37
 * @desc 直接修改文件内存中的数据
 */
public class RAMFileDemo {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("C:\\Users\\Administrator\\Desktop\\file.txt"), "rw");


        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'a');
        map.put(3, (byte) 'b');

        randomAccessFile.close();

    }
}
