package wang.huaiting.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899); // 非阻塞的 server socket

        // 高可用的 THsHaServer 设置器, 并设置多线程
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        // 具体业务处理器
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        // 工厂
        arg.protocolFactory(new TCompactProtocol.Factory()); // 协议层，TCompactProtocol 二进制压缩协议
        arg.transportFactory(new TFramedTransport.Factory()); // 传输层，TFramedTransport 以二进制帧传递
        arg.processorFactory(new TProcessorFactory(processor)); // 处理器

        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Started!");

        server.serve(); // 启动，一个异步非阻塞死循环
    }
}
