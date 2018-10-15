package wang.huaiting.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {
    public static void main(String[] args) throws IOException {
        // 内存映射文件 MappedByteBuffer
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // map (访问模式， 映射起始位置， 映射数量)
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'a'); // 修改文件内容
        mappedByteBuffer.put(3, (byte)'b');

        randomAccessFile.close();

    }
}
