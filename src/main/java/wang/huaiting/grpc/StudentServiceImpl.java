package wang.huaiting.grpc;

import io.grpc.stub.StreamObserver;
import wang.huaiting.proto.*;

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
}
