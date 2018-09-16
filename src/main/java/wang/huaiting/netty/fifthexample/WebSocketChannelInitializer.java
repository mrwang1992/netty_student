package wang.huaiting.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new ChunkedWriteHandler());
        // Aggregator 聚合, 消息聚合
        channelPipeline.addLast(new HttpObjectAggregator(8192));

        // webSocket 处理相关
        // 负责websocket握手， ping pong（心跳），文本或二进制类数据，会传给pipline 下一个 handler进行处理
        //  frames 是传输的基本
        // 构造参数 websocket path，协定了 链接 协议 ws://server:port/{path}
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        channelPipeline.addLast(new TextWebSocketFrameHandler());
    }
}
