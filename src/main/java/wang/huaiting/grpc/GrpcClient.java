package wang.huaiting.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wang.huaiting.proto.MyRequest;
import wang.huaiting.proto.MyResponse;
import wang.huaiting.proto.StudentServiceGrpc;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        for (int i = 0; i < 20; i++) {
            MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("lisi").build());

            System.out.println(myResponse.getRalname());
        }

    }
}
