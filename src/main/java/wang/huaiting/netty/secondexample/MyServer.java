package wang.huaiting.netty.secondexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyServer {
    private static InternalLogger log = InternalLoggerFactory.getInstance(MyServer.class);

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.WARN)) // 添加日志 Handler
                    .childHandler(new MyServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
//            channelFuture.channel().closeFuture().sync();
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("close server");
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            });
        }
        finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            log.info("ShutdownHook execute start...");
            log.info("Netty NioEventLoopGroup shutdownGracefully...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("ShutdownHook execute end ...");
        }, ""));
//        TimeUnit.SECONDS.sleep(7);
//        System.exit(0);
    }


}
