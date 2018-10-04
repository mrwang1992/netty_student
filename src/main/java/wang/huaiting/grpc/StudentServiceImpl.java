package wang.huaiting.grpc;

import io.grpc.stub.StreamObserver;
import wang.huaiting.proto.*;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端消息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRalname("zhangsan").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到的客户端信息：" + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder()
                .setName("zhangsan").setAge(20).setCity("beijing").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setName("lisi").setAge(20).setCity("beijing").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setName("wangwu").setAge(20).setCity("beijing").build());
        responseObserver.onNext(StudentResponse.newBuilder()
                .setName("zhaoliu").setAge(20).setCity("beijing").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext: " + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse1 = StudentResponse.newBuilder()
                        .setName("zhangsan").setAge(21).setCity("beijing").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder()
                        .setName("wangwu").setAge(22).setCity("beijing").build();
                StudentResponse studentResponse3 = StudentResponse.newBuilder()
                        .setName("zhaoliu").setAge(23).setCity("beijing").build();
                StudentResponse studentResponse4 = StudentResponse.newBuilder()
                        .setName("lisi").setAge(24).setCity("beijing").build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse1)
                        .addStudentResponse(studentResponse2)
                        .addStudentResponse(studentResponse3)
                        .addStudentResponse(studentResponse4)
                        .build();

                responseObserver.onNext(studentResponseList);
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
