// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package wang.huaiting.proto;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wang_huaitng_proto_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wang_huaitng_proto_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wang_huaitng_proto_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wang_huaitng_proto_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wang_huaitng_proto_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wang_huaitng_proto_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wang_huaitng_proto_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wang_huaitng_proto_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_wang_huaitng_proto_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_wang_huaitng_proto_StudentResponseList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\022wang.huaitng.proto\"\035\n\tM" +
      "yRequest\022\020\n\010username\030\001 \001(\t\"\035\n\nMyResponse" +
      "\022\017\n\007ralname\030\002 \001(\t\"\035\n\016StudentRequest\022\013\n\003a" +
      "ge\030\001 \001(\005\":\n\017StudentResponse\022\014\n\004name\030\001 \001(" +
      "\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"S\n\023StudentR" +
      "esponseList\022<\n\017studentResponse\030\001 \003(\0132#.w" +
      "ang.huaitng.proto.StudentResponse2\267\002\n\016St" +
      "udentService\022X\n\025GetRealNameByUsername\022\035." +
      "wang.huaitng.proto.MyRequest\032\036.wang.huai" +
      "tng.proto.MyResponse\"\000\022_\n\020GetStudentsByA",
      "ge\022\".wang.huaitng.proto.StudentRequest\032#" +
      ".wang.huaitng.proto.StudentResponse\"\0000\001\022" +
      "j\n\027GetStudentWrapperByAges\022\".wang.huaitn" +
      "g.proto.StudentRequest\032\'.wang.huaitng.pr" +
      "oto.StudentResponseList\"\000(\001B%\n\023wang.huai" +
      "ting.protoB\014StudentProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_wang_huaitng_proto_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_wang_huaitng_proto_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wang_huaitng_proto_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_wang_huaitng_proto_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_wang_huaitng_proto_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wang_huaitng_proto_MyResponse_descriptor,
        new String[] { "Ralname", });
    internal_static_wang_huaitng_proto_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_wang_huaitng_proto_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wang_huaitng_proto_StudentRequest_descriptor,
        new String[] { "Age", });
    internal_static_wang_huaitng_proto_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_wang_huaitng_proto_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wang_huaitng_proto_StudentResponse_descriptor,
        new String[] { "Name", "Age", "City", });
    internal_static_wang_huaitng_proto_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_wang_huaitng_proto_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_wang_huaitng_proto_StudentResponseList_descriptor,
        new String[] { "StudentResponse", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
