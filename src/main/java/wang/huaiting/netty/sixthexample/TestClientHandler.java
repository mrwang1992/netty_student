package wang.huaiting.netty.sixthexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                                            .setName("wanghuaiting")
                                            .setAge(26).setAddress("beijing").build();

        channel.writeAndFlush(person);
    }
}
