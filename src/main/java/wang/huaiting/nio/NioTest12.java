package wang.huaiting.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 关于 selector
 * <p>
 * 监听 五个端口号，使用一个线程处理所有链接过来的客户端
 */
public class NioTest12 {
    public static void main(String[] args) throws IOException {

        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        // 调用open静态方法 构造selector
        Selector selector = Selector.open();

        // 查看系统用的 select 是哪个实现
        System.out.println("Class: " + selector.getClass());

        // 一个 selector 监听多个端口
        for (int i = 0; i < ports.length; ++i) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false); // 调整阻塞的模式，false 非阻塞， true 阻塞
            ServerSocket serverSocket = serverSocketChannel.socket(); // 获取与这个channel关联的 socket
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);

            serverSocket.bind(inetSocketAddress);

            // 将 channel 注册到 selector 当中 并且声明感兴趣 连接的事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: " + ports[i]);
        }

        while (true) {
            // 返回 selector 当中存在selectionKey的数量， 表明selector中存在事件， 其是阻塞的
            int numbers = selector.select();
            System.out.println("numbers: " + numbers);

            // 返回 selectionKey 集合，
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            System.out.println("selectionKeys: " + selectionKeySet);

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();

            while (iterator.hasNext()) {
                // 验证为单线程 模型
                System.out.println("thread id: " + Thread.currentThread().getId());

                SelectionKey selectionKey = iterator.next();

                // isAcceptable 发生已经可以连接的事件
                if (selectionKey.isAcceptable()) {
                    // 通过 selectionKey 获取到channel 进行进一步处理
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 设置为 非阻塞 socketChannel 操作
                    socketChannel.configureBlocking(false);

                    // 讲连接的 channel 注册到selector 当中，表明为可读
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    // 一定要调用remove进行删除，不然事件还会被获取到 触发异常
                    iterator.remove();

                    System.out.println("获得客户端连接: " + socketChannel);

                } else if (selectionKey.isReadable()) { // selectionKey 为可读事件 进入到读取逻辑

                    // 代码逻辑同上
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    // 读取数据
                    int bytesRead = 0;

                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                    while (true) {

                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0) {
                            break;
                        }

                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);

                        bytesRead += read;
                    }

                    System.out.println("读取： " + bytesRead + " 来自于: " + socketChannel);

                    iterator.remove();
                }
            }
        }

    }
}
