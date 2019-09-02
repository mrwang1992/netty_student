package wang.huaiting.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *  关于Buffer的Scattering 与 Gathering
 *
 *  分散
 *      不仅可以传单个buffer 还可以传 多个 buffer数组
 *      将来自一个channel当中的数据 传给buffer数组，只有第一个写满后才会写入第二个 顺序的
 *  合并
 *      讲buffer数组读出到外部 并且是顺序的， 特性同上
 *
 *  Test client
 *      nc localhost 8899
 *      hellowor
 */
public class NioTest11 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocateDirect(2);
        byteBuffers[1] = ByteBuffer.allocateDirect(3);
        byteBuffers[2] = ByteBuffer.allocateDirect(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = socketChannel.read(byteBuffers);
                byteRead += r;

                System.out.println("byte read:" + byteRead);

                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.flip();
            });

            long bytesWrite = 0;
            while (bytesWrite < messageLength) {
                long r = socketChannel.write(byteBuffers);
                bytesWrite += r;
            }

            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("byte read: " + byteRead + ", byte write: " + bytesWrite + ", messageLength: " + messageLength);
        }

    }
}
