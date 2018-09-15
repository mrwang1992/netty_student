package wang.huaiting.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 需求
 * 服务器启动
 * 第一个用户连接 无提示 之后的用户连接后 服务器打印出来 xxx 上线， 用户掉线后 服务器打印 xxx 下线
 * <p>
 * 客户端
 * 自己上线不提示自己上线， 上线一个 则收到广播xxx已上线
 * 发送一条消息 自己 显示 自己发送：{msg}, 其余人显示 xxx发送：{msg}
 * <p>
 * 主要考察点 handler 生命周期方法
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    // netty 提供管理channel的类
    // 单例模式 事件线程执行器（详细了解 还是跟到类源码里了解）
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 客户端链接建立 事件

        // 获取当前连接上来的客户端channel对象
        Channel channel = ctx.channel();

        // 服务端对客户端广播xxx 上线
        // 先广播(write, writeAndFlush) 再添加 就不会广播到自己
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 加入 \n");

        // netty 提供了ChannelGroup 对象 就是用于保存已经链接的channel对象用的
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 客户端断开事件

        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器] - " +
                " 用户 {" + channel.remoteAddress() +"} 离开， 当前用户数：" + channelGroup.size() + " \n");

        // 链接断开后删除客户端channel（netty其实会自动调用，写不写都可以）
//        channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 链接活动状态
        Channel channel = ctx.channel();
        // 服务端打印
        System.out.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到客户端发过来的消息

        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            // 判断是不是自己 因为需求里 自己收到自己的消息 跟 别人收到自己的消息不一样
            if (channel != ch) { // 别人会收到
                ch.writeAndFlush(channel.remoteAddress() + " 发送的消息: " + msg + "\n");
            } else { // 自己收到
                ch.writeAndFlush("[自己] " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
