package wang.huaiting.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * server <-> client 交互 聊天服务器 nio 实现
 * <p>
 * 服务端只有一个线程
 * <p>
 * 客户端发送过来信息后 广播给其他客户端
 */
public class NioTest13_Server {

    // 用map来保存所有客户端的channel
    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        // 四行 模板代码
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        // 创建 selector 对象
        Selector selector = Selector.open();

        // 注册 ACCEPT 事件，发生并就绪 既触发
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 事件处理
        while (true) {
            try {
                // 阻塞到关注的事件发生 并返回 关注事件发生的数量
                int numbers = selector.select();

                // 返回后就能获取 发生的事件 的 selectionKey 集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {

                    final SocketChannel client;

                    try {
                        // 表示客户端向服务端发起一次链接
                        if (selectionKey.isAcceptable()) {
                            // 通过 selectionKey 获取到 channel
                            // 为什么 我们能强转为 ServerSocketChannel ， 因为我们只注册了 ServerSocketChannel
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                            client = server.accept();
                            client.configureBlocking(false);

                            // 将自身注册为 OP_READ 事件
                            client.register(selector, SelectionKey.OP_READ);

                            // 将链接上来的 channle 放到 map当中进行保存
                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);

                        } else if (selectionKey.isReadable()) { // 因为 我们只对 soekctChannel 才注册了 read 事件，所以可以直接转
                            client = (SocketChannel) selectionKey.channel();

                            // 这里没有加循环 最多 只读取 1024 个
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);

                            if (count > 0) {
                                readBuffer.flip();

                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client + ": " + receivedMessage);

                                String sendKey = null;

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        sendKey = entry.getKey();
                                        break;
                                    }
                                }

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((sendKey + ": " + receivedMessage).getBytes());

                                    writeBuffer.flip();

                                    value.write(writeBuffer);
                                }

                            }
                        }

                        selectionKeys.clear();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                });


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}
