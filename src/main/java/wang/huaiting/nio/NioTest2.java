package wang.huaiting.nio;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        fileChannel.read(byteBuffer); // channel 读到buffer中

        byteBuffer.flip();


        while (byteBuffer.remaining() > 0) { // remaining 方法获取buffer中还有多少数据
            byte b = byteBuffer.get();

            System.out.println("Character: " + (char)b);
        }

        fileInputStream.close();

    }
}
