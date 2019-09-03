package wang.huaiting.self_netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.URI;

public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private JedisPool jedisPool = null;

    public TestServerHandler(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        Jedis jedis = this.jedisPool.getResource();

        System.out.println(jedis.get("hello"));

        jedis.close();

        if (msg instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest) msg;

            System.out.println("请求方法名: " + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());

            if ("/favicon.ico".equals(uri.getPath())) {
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

            ctx.close();
        }
    }
}
