package wang.huaiting.netty_new_student.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;

import java.nio.charset.Charset;
import java.security.SecureRandom;

public class Test01 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(100);

        while (byteBuf.writableBytes() >= 4) {
            int randomNumber = new SecureRandom().nextInt(20);
            byteBuf.writeInt(randomNumber);
        }

        byteBuf.forEachByte(ByteProcessor.FIND_CR);

        while (byteBuf.isReadable()) {
            System.out.println(byteBuf.readInt());
            System.out.println(byteBuf);
        }


        // ================== 切片共享底层存储 ===================

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) == sliced.getByte(0);

        // ================== 复制 独立底层存储 =====================
        buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 15);
        System.out.println(copy.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) != copy.getByte(0);

        // ================= 引用计数 =====================

        buf = Unpooled.directBuffer(10);

        System.out.println(buf.refCnt());
        buf.release();
        try {
            // 使用已经释放的ByteBuf 会触发 IllegalReferenceCountException: refCnt: 0 异常
            buf.writeByte(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
