package wang.huaiting.asynctest;

import java.util.concurrent.*;

public class FutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("耗时操作");
                timeConsumingOperation();
                return 100;
            }
        });

        System.out.println("计算结果::" + future.get());
        System.out.println("主线程耗时::" + (System.currentTimeMillis() - l));

    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
