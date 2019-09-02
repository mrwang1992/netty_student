package wang.huaiting.asynctest;

public class SyncDemo {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int i = syncCalculate();
        System.out.println("计算结果是::" + i);
        System.out.println("主线程运算耗时: " + (System.currentTimeMillis() - l));
    }

    static int syncCalculate() {
        System.out.println("耗时操作");
        timeConsumingOperation();
        return 100;
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
