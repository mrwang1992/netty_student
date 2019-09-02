package wang.huaiting.asynctest;

import io.netty.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class NettyFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Future<Integer> f = group.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行耗时操作");
                timeConsumingOperation();
                return 100;
            }
        });
        f.addListener(new FutureListener<Object>() {
            @Override
            public void operationComplete(Future<Object> future) throws Exception {
                System.out.println("计算结果:" + future.get());
            }
        });
        System.out.println("主线程运算耗时: " + (System.currentTimeMillis() - l));
        new CountDownLatch(1).await();
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
