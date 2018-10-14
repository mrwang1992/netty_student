package wang.huaiting.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        // 用 nio 的形式 生成一些随机数 并打印
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity: " + buffer.capacity());

        for (int i = 0; i < buffer.capacity(); ++i) {
            int randomNumber = new SecureRandom().nextInt(20);

            buffer.put(randomNumber);
        }

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
