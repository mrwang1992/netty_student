package wang.huaiting.selfserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.redis.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyServer {
    private final EventLoopGroup eventLoopGroupBoss;
    private final EventLoopGroup eventLoopGroupSelector;
    private final ServerBootstrap serverBootstrap;
    private DefaultEventExecutorGroup defaultEventExecutorGroup;

    private final ExecutorService publicExecutor;


    public NettyServer(ServerConfig serverConfig) {
        this.serverBootstrap = new ServerBootstrap();

        int publicThreadNums = 4;
        this.publicExecutor = Executors.newFixedThreadPool(publicThreadNums, new ThreadFactory() {

            private AtomicInteger threadIndex = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new FastThreadLocalThread(r,
                        String.format("NettyServerPublicExecutor_%d", this.threadIndex.incrementAndGet()));
            }
        });

        // 调度线程 1个就可以了
        this.eventLoopGroupBoss = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new FastThreadLocalThread(r, String.format("NettyBoss_%d", this.threadIndex.incrementAndGet()));
            }
        });

        // selector 线程默认启用3个
        int selectorNums = 3;

        if (useEpoll()) {
            this.eventLoopGroupSelector = new EpollEventLoopGroup(selectorNums, new ThreadFactory() {
                private AtomicInteger threadIndex = new AtomicInteger(0);
                private int threadTotal = selectorNums;

                @Override
                public Thread newThread(Runnable r) {
                    return new FastThreadLocalThread(r, String.format("NettyServerEPOLLSelector_%d_%d",
                            threadTotal, this.threadIndex.incrementAndGet()));
                }
            });
        } else {
            this.eventLoopGroupSelector = new NioEventLoopGroup(selectorNums, new ThreadFactory() {
                private AtomicInteger threadIndex = new AtomicInteger(0);
                private int threadTotal = selectorNums;

                @Override
                public Thread newThread(Runnable r) {
                    return new FastThreadLocalThread(r, String.format("NettyServerNIOSelector_%d_%d",
                            threadTotal, this.threadIndex.incrementAndGet()));
                }
            });
        }
    }

    public boolean start() {
        this.defaultEventExecutorGroup = new DefaultEventExecutorGroup(
                8,
                new ThreadFactory() {
                    private AtomicInteger threadIndex = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new FastThreadLocalThread(r,
                                "NettyServerCodecThread_" + this.threadIndex.incrementAndGet());
                    }
                }
        );

        ServerBootstrap childHandler =
                this.serverBootstrap.group(this.eventLoopGroupBoss, this.eventLoopGroupSelector)
                        .channel(useEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        .option(ChannelOption.SO_REUSEADDR, true)
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        .handler(new LoggingHandler(LogLevel.INFO)) // 添加日志 Handler
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                ChannelPipeline channelPipeline = ch.pipeline();
                                channelPipeline.addLast(defaultEventExecutorGroup,
                                        new RedisDecoder(),
                                        new RedisBulkStringAggregator(),
                                        new RedisArrayAggregator(),
                                        new RedisEncoder(),
                                        new SimpleChannelInboundHandler<ArrayRedisMessage>() {
                                            @Override
                                            protected void channelRead0(ChannelHandlerContext ctx, ArrayRedisMessage msg) {
                                                for (RedisMessage redisMessage : msg.children()) {
                                                    FullBulkStringRedisMessage fullBulkStringRedisMessage =
                                                            (FullBulkStringRedisMessage) redisMessage;
                                                    System.out.println(fullBulkStringRedisMessage
                                                            .content().toString(Charset.forName("utf-8")));
                                                }
                                                ctx.writeAndFlush(new SimpleStringRedisMessage("gogogo"));
                                            }
                                        }
                                );
                            }
                        });

        try {
            ChannelFuture sync = this.serverBootstrap.bind(8899).sync();
        } catch (InterruptedException e1) {
            throw new RuntimeException("netty server start err: ", e1);
        }

        return true;
    }

    /**
     * 判断是否启动 Epoll
     *
     * @return
     */
    private boolean useEpoll() {
        // TODO: 先写死其实只在 linux的时候启动 Epoll
        return false;
    }
}
