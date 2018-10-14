package wang.huaiting.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear(); // 注释掉会变成死循环，具体原因 可以查看一下 clear 和 flip 的影响

            int read = inputChannel.read(buffer); // 读取数据到buffer 里， 如果read 为-1 则读取结束

            System.out.println("read: " + read); // 可以通过改变 allocate 大小控制一次读取数据数量

            if (-1 == read) {
                break;
            }

            buffer.flip();
            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
