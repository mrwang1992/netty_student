package wang.huaiting.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wang.huaiting.proto.*;

import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        for (int i = 0; i < 20; i++) {
            MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("lisi").build());

            System.out.println(myResponse.getRalname());
        }

        System.out.println(" ===== getStudentsByAge ====== ");

        Iterator<StudentResponse> studentResponses = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(12).build());

        while (studentResponses.hasNext()) {
            System.out.println("==> " + studentResponses.next());
        }
    }
}
