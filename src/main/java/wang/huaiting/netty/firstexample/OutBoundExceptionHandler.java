package wang.huaiting.netty.firstexample;

import io.netty.channel.*;

public class OutBoundExceptionHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundExceptionHandler this");
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                    future.channel().close();
                } else {
                    System.out.println("isSuccess");
                }
            }
        });
    }
}
