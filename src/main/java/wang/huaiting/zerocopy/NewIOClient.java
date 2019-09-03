package wang.huaiting.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));

        socketChannel.configureBlocking(true);

        String fileName = "/Users/wht/Downloads/gimp-2.10.6-x86_64.dmg";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferCount + ", 耗时：" + (System.currentTimeMillis() - startTime));
    }
}