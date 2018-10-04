package wang.huaiting.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import wang.huaiting.proto.*;

import java.time.LocalDateTime;
import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        for (int i = 0; i < 20; i++) {
            MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("lisi").build());

            System.out.println(myResponse.getRalname());
        }

        System.out.println(" ===== getStudentsByAge ====== ");

        Iterator<StudentResponse> studentResponses = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(12).build());

        while (studentResponses.hasNext()) {
            System.out.println("==> " + studentResponses.next());
        }

        System.out.println(" ===== getStudentWrapperByAges ====== ");

        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                System.out.println("=== onNext ====");
                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.print(studentResponse.getName() + " ");
                    System.out.print(studentResponse.getAge() + " ");
                    System.out.print(studentResponse.getCity());
                    System.out.println("\n==");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        };
        // 客户端对服务端进行流式调用 那么 就只能用异步的方式进行调用
        StreamObserver<StudentRequest> studentRequestStreamObserver =
                stub.getStudentWrapperByAges(studentResponseListStreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());

        studentRequestStreamObserver.onCompleted();


        System.out.println(" ===== biTalk ====== ");

        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("client onCompleted");
            }
        });

        for (int i = 0; i < 10; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

        // 异步调用 所以要等一下服务器返回
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
