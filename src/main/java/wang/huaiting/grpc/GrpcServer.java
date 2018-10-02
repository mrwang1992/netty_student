package wang.huaiting.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    private Server server;

    private void start() throws Exception {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();

        System.out.println("Server started!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭Grpc Server");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里");
    }

    private void stop() {
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws Exception {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        GrpcServer server = new GrpcServer();

        server.start();

        // 不加这行 服务不会等待，直接启动退出
        server.awaitTermination();
    }
}
