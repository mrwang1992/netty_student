package wang.huaiting.nio;


import java.nio.ByteBuffer;

public class NioTest6 {
    public static void main(String[] args) {
        //  buffer 分片， slice的概念
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte)i);
        }

        buffer.position(2); // 设置 起始位置
        buffer.limit(6); // 设置 截止位置

        ByteBuffer sliceBuffer = buffer.slice();

        for (int i =0; i < sliceBuffer.capacity(); ++i) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
