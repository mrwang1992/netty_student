package wang.huaiting.netty.asyncexample;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AioTest {
    public static void main(String[] args) throws Exception {
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(10));
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup).bind(new InetSocketAddress(8081));

        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Future<Integer> future = result.read(buffer);
                serverSocketChannel.accept(null, this);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

        new CountDownLatch(1).await();
    }
}
