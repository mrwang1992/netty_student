package wang.huaiting.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestHTTPServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        // 请求到自己定义通道处理器逻辑里，可以做自己的响应逻辑

        // 需要进行判断 不然会出现 异常
        //  An exceptionCaught() event was fired,
        //  java.io.IOException: Connection reset by peer
        if (msg instanceof HttpRequest) {

            // 组织一个 bytebuf
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

            // 构建 response
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 写回客户端 直接 write 是写到缓冲区， 要选用 writeAndFlush
            ctx.writeAndFlush(response);
        }
    }
}
