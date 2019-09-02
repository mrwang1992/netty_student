package wang.huaiting.selfserver;

public class ServerConfig {
    private int port;

    public ServerConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
