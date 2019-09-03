package wang.huaiting.self_netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyServer {

    private static JedisPool create_jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置 redis 连接池最大连接数量
        jedisPoolConfig.setMaxTotal(50);
        //设置 redis 连接池最大空闲连接数量
        jedisPoolConfig.setMaxIdle(10);
        //设置 redis 连接池最小空闲连接数量
        jedisPoolConfig.setMinIdle(4);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 60);

        return jedisPool;
    }

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        JedisPool jedisPool = create_jedisPool();

        try {
            // 帮助启动的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 定义数据交互通道
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 定义子处理器
                    .childHandler(new TestServerInitalizer(jedisPool));
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
