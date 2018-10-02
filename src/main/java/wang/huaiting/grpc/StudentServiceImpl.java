package wang.huaiting.grpc;

import io.grpc.stub.StreamObserver;
import wang.huaiting.proto.MyRequest;
import wang.huaiting.proto.MyResponse;
import wang.huaiting.proto.StudentServiceGrpc;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端消息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRalname("zhangsan").build());
        responseObserver.onCompleted();
    }
}
