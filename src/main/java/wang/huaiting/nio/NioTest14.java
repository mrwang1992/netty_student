package wang.huaiting.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * class 45 字节码
 *  文件拷贝
 *
 */
public class NioTest14 {
    public static void main(String[] args) throws IOException {
        String inputFile = "NioTest14_In.txt";
        String outputFile = "NioTest14_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");

        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        System.out.println("====== 获取系统支持的所有字符集 ======");

        Charset.availableCharsets().entrySet().forEach(stringCharsetEntry -> {
            System.out.println(stringCharsetEntry.getKey() + " : " + stringCharsetEntry.getValue());
        });

        System.out.println("============");


//        Charset charset = Charset.forName("utf-8");
        Charset charset = Charset.forName("iso-8859-1"); // 为什么 中文在这里 也没有问题

        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);

        ByteBuffer outputData = encoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

    }
}
