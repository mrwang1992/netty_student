package wang.huaiting.netty.fourhexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new IdleStateHandler(
            5, // 没有进行读操作时间
                7, // 没有进行写操作时间
                10, // 没有进行读写操作时间
                TimeUnit.SECONDS // 时间单位
        )); // 针对空闲检测提供的 handler, 当一个channel没有读写动作时会被触发事件

        channelPipeline.addLast(new MyServerHandler());
    }
}
