package wang.huaiting.netty.sixthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());

        // 设定自己定义的 MessageType
        channelPipeline.addLast(new ProtobufDecoder(
                MyDataInfo.MyMessage.getDefaultInstance()
        ));

        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast(new ProtobufEncoder());

        channelPipeline.addLast(new TestServerHandler());
    }
}
