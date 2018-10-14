package wang.huaiting.nio;

import java.nio.ByteBuffer;

public class NioTest7 {
    public static void main(String[] args) {
        // 只读buffer

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        // 生成 HeapByteBufferR 的只读buffer 实现类，但是底层数组是共享的，所以原先的buffer进行改变 在readOnlyBuffer中也是可见的

        System.out.println(readOnlyBuffer.getClass());

//        readOnlyBuffer.position(0);
//        readOnlyBuffer.put((byte)2);  // ReadOnlyBufferException


    }
}
