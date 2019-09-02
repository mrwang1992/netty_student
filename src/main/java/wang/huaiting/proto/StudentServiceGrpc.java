package wang.huaiting.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "wang.huaitng.proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<wang.huaiting.proto.MyRequest,
      wang.huaiting.proto.MyResponse> METHOD_GET_REAL_NAME_BY_USERNAME =
      io.grpc.MethodDescriptor.<wang.huaiting.proto.MyRequest, wang.huaiting.proto.MyResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "wang.huaitng.proto.StudentService", "GetRealNameByUsername"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.MyRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.MyResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<wang.huaiting.proto.StudentRequest,
      wang.huaiting.proto.StudentResponse> METHOD_GET_STUDENTS_BY_AGE =
      io.grpc.MethodDescriptor.<wang.huaiting.proto.StudentRequest, wang.huaiting.proto.StudentResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "wang.huaitng.proto.StudentService", "GetStudentsByAge"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StudentRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StudentResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<wang.huaiting.proto.StudentRequest,
      wang.huaiting.proto.StudentResponseList> METHOD_GET_STUDENT_WRAPPER_BY_AGES =
      io.grpc.MethodDescriptor.<wang.huaiting.proto.StudentRequest, wang.huaiting.proto.StudentResponseList>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "wang.huaitng.proto.StudentService", "GetStudentWrapperByAges"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StudentRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StudentResponseList.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<wang.huaiting.proto.StreamRequest,
      wang.huaiting.proto.StreamResponse> METHOD_BI_TALK =
      io.grpc.MethodDescriptor.<wang.huaiting.proto.StreamRequest, wang.huaiting.proto.StreamResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "wang.huaitng.proto.StudentService", "BiTalk"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StreamRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              wang.huaiting.proto.StreamResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(wang.huaiting.proto.MyRequest request,
        io.grpc.stub.StreamObserver<wang.huaiting.proto.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_REAL_NAME_BY_USERNAME, responseObserver);
    }

    /**
     * <pre>
     * 返回流式数据响应 规范 要求请求和返回必须为 message 类型的 所以不能直接用 int32
     * </pre>
     */
    public void getStudentsByAge(wang.huaiting.proto.StudentRequest request,
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_STUDENTS_BY_AGE, responseObserver);
    }

    /**
     * <pre>
     * 发送流数据 返回list
     * </pre>
     */
    public io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentRequest> getStudentWrapperByAges(
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_GET_STUDENT_WRAPPER_BY_AGES, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<wang.huaiting.proto.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_BI_TALK, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_REAL_NAME_BY_USERNAME,
            asyncUnaryCall(
              new MethodHandlers<
                wang.huaiting.proto.MyRequest,
                wang.huaiting.proto.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            METHOD_GET_STUDENTS_BY_AGE,
            asyncServerStreamingCall(
              new MethodHandlers<
                wang.huaiting.proto.StudentRequest,
                wang.huaiting.proto.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            METHOD_GET_STUDENT_WRAPPER_BY_AGES,
            asyncClientStreamingCall(
              new MethodHandlers<
                wang.huaiting.proto.StudentRequest,
                wang.huaiting.proto.StudentResponseList>(
                  this, METHODID_GET_STUDENT_WRAPPER_BY_AGES)))
          .addMethod(
            METHOD_BI_TALK,
            asyncBidiStreamingCall(
              new MethodHandlers<
                wang.huaiting.proto.StreamRequest,
                wang.huaiting.proto.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(wang.huaiting.proto.MyRequest request,
        io.grpc.stub.StreamObserver<wang.huaiting.proto.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 返回流式数据响应 规范 要求请求和返回必须为 message 类型的 所以不能直接用 int32
     * </pre>
     */
    public void getStudentsByAge(wang.huaiting.proto.StudentRequest request,
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_STUDENTS_BY_AGE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 发送流数据 返回list
     * </pre>
     */
    public io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentRequest> getStudentWrapperByAges(
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_GET_STUDENT_WRAPPER_BY_AGES, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<wang.huaiting.proto.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<wang.huaiting.proto.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_BI_TALK, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public wang.huaiting.proto.MyResponse getRealNameByUsername(wang.huaiting.proto.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions(), request);
    }

    /**
     * <pre>
     * 返回流式数据响应 规范 要求请求和返回必须为 message 类型的 所以不能直接用 int32
     * </pre>
     */
    public java.util.Iterator<wang.huaiting.proto.StudentResponse> getStudentsByAge(
        wang.huaiting.proto.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_STUDENTS_BY_AGE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<wang.huaiting.proto.MyResponse> getRealNameByUsername(
        wang.huaiting.proto.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENT_WRAPPER_BY_AGES = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((wang.huaiting.proto.MyRequest) request,
              (io.grpc.stub.StreamObserver<wang.huaiting.proto.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((wang.huaiting.proto.StudentRequest) request,
              (io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENT_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentWrapperByAges(
              (io.grpc.stub.StreamObserver<wang.huaiting.proto.StudentResponseList>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<wang.huaiting.proto.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class StudentServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return wang.huaiting.proto.StudentProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceDescriptorSupplier())
              .addMethod(METHOD_GET_REAL_NAME_BY_USERNAME)
              .addMethod(METHOD_GET_STUDENTS_BY_AGE)
              .addMethod(METHOD_GET_STUDENT_WRAPPER_BY_AGES)
              .addMethod(METHOD_BI_TALK)
              .build();
        }
      }
    }
    return result;
  }
}
