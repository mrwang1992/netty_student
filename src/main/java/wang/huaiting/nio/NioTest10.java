package wang.huaiting.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {
    public static void main(String[] args) throws IOException {
        // 文件锁概念

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        // （锁位置， 锁多长， true 共享锁 false 排它锁）
        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("valid: " + fileLock.isValid());
        System.out.println("lock type: " + fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();

    }
}
