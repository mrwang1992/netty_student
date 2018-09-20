package wang.huaiting.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage myMessage = msg;
        System.out.println(msg);

        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if (dataType == MyDataInfo.MyMessage.DataType.PersonType) {
            MyDataInfo.Person person = msg.getPerson();

            System.out.println(person);
        } else if (dataType == MyDataInfo.MyMessage.DataType.DogType) {
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog);
        } else {
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
