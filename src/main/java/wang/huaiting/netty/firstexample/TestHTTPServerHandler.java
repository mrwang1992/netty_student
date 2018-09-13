package wang.huaiting.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHTTPServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 请求到自己定义通道处理器逻辑里，可以做自己的响应逻辑

//        看一下 可能是什么类型的
//        class io.netty.handler.codec.http.DefaultHttpRequest
//        请求方法名: POST
//        class io.netty.handler.codec.http.LastHttpContent$1
        System.out.println(msg.getClass());

        // 打印远程地址
        // /0:0:0:0:0:0:0:1:54190
        System.out.println("远程地址: " + ctx.channel().remoteAddress());

        // 可以暂停看看交互
//        Thread.sleep(8000);

//        $ lsof -i:8899
//        COMMAND   PID USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
//        java    13289  wht  171u  IPv6 0xcd4a29a6478691db      0t0  TCP *:8899 (LISTEN)
//                java    13289  wht  175u  IPv6 0xcd4a29a647869d5b      0t0  TCP localhost:8899->localhost:54720 (ESTABLISHED)
//                java    13289  wht  177u  IPv6 0xcd4a29a6520cafdb      0t0  TCP localhost:8899->localhost:54725 (ESTABLISHED)
//                curl    13386  wht    5u  IPv6 0xcd4a29a64786a8db      0t0  TCP localhost:54720->localhost:8899 (ESTABLISHED)
//                curl    13387  wht    5u  IPv6 0xcd4a29a64786ba1b      0t0  TCP localhost:54725->localhost:8899 (ESTABLISHED)


        // 需要进行判断 不然会出现 异常
        //  An exceptionCaught() event was fired,
        //  java.io.IOException: Connection reset by peer
        if (msg instanceof HttpRequest) {

            // 其实是啰嗦了，因为前面已经判断过
            // 通过转换后的对象我们能拿到 http 请求体相关信息
            HttpRequest httpRequest = (HttpRequest) msg;

            System.out.println("请求方法名: " + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());

            if ("/favicon.ico".equals(uri.getPath())) {
                // 如果为 favicon.ico 则不进行接下来的处理
                System.out.println("这次请求为 favicon.ico ");
                return;
            }

            // 组织一个 bytebuf
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

            // 构建 response
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 写回客户端 直接 write 是写到缓冲区， 要选用 writeAndFlush
            ctx.writeAndFlush(response);

            // 全双工模式下，服务端也能够主动关闭请求
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当channel 活跃时进入这个方法
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // 注册
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 新的通道出现
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // channel 活跃状态
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // 取消注册
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }

// 执行顺序（浏览器执行的话，不会马上放开链接，所以最后两方法得等一会才会执行 复现的话 马上关闭浏览器就会马上断开执行）
// 问题其实在于 netty 设计是按照网络框架进行设计的所以 是用的网络思维来进行开发理解 服务端调用 ctx.close(); 也能主动断开
//    handler added
//    channel registered
//    channel active
//    请求方法名: POST
//    channel inactive
//    channel unregistered


}
