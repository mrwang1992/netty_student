package wang.huaiting.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {
        // 事件循环线程组
        // 可以理解为两个死循环等待事件进入处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 获取连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(20); // 处理连接

        try {
            // 帮助启动的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 定义数据交互通道
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 定义子处理器
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
