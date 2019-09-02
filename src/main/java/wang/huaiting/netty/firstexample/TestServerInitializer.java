package wang.huaiting.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        // 连接注册后则创建， 回调方法
        ChannelPipeline pipeline = ch.pipeline();

        // 多种add 可以加在前面或者后面，顺序执行
        pipeline.addLast("httpServerCodec", new HttpServerCodec()); // 加一个http编解码器
        pipeline.addLast("testHttpServerHandler", new TestHTTPServerHandler()); // 进入到自定义的handler里

        pipeline.addFirst("outbound", new OutBoundExceptionHandler());

    }
}
