package wang.huaiting.protobuf;


/**
 * 目标 生成一个对象 然后 对象转为 byte 数组，并通过数组 再转化为对象
 */
public class ProtoBufTest {
    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("zhangsan")
                .setAge(20)
                .setAddress("北京").build();

        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.toString());
    }
}
