package wang.huaiting.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {
    public static void main(String[] args) {
        // 传输协议要与服务端对应保持一致
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            // 打开传输
            transport.open();

            // 调用方法
            Person person = client.getPersonByUsername("wanghuaiting");
            System.out.println(person);

            System.out.println("========");

            Person person1 = new Person();
            person1.setUsername("lisi");
            person1.setAge(30);
            person1.setMarried(true);
            client.savePerson(person1);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }

    }
}
