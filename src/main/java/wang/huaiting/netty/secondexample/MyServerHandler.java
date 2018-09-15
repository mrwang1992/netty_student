package wang.huaiting.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // ChannelHandlerContext ctx 上下文，连接对象， 各种信息，
        // String msg 具体发过来的信息
        System.out.println("远程地址：" + ctx.channel().remoteAddress() + ", " + msg);

        ctx.channel().writeAndFlush("receive data: " + UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常时方法被调用

        // 打印异常
        cause.printStackTrace();
        // 关闭当前链接
        ctx.close();
    }
}
