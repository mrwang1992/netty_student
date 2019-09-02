package wang.huaiting.selfserver;

public class ServerStartup {
    public static void main(String[] args) {
        ServerController sc = createServerController(args);
        sc.initialize();
        sc.start();
    }

    /**
     * 创建并初始化一个 ServerController
     * @param args
     * @return
     */
    private static ServerController createServerController(String[] args) {
        return new ServerController();
    }
}
