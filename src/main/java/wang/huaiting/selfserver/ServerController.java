package wang.huaiting.selfserver;

public class ServerController {

    private ServerConfig serverConfig;

    private NettyServer nettyServer;

    public ServerController() {
        this(new ServerConfig(8899));
    }

    public ServerController(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public boolean initialize() {
        this.nettyServer = new NettyServer(this.serverConfig);
        return true;
    }

    public boolean start() {
        return this.nettyServer.start();
    }
}
