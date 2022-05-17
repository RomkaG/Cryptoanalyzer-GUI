package sample;//запись в файл

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileWriter {

    static void fileWriter (String path_dest, String str) throws IOException {
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(path_dest,"rw");
        try (FileChannel fileChannel = randomAccessFile2.getChannel()){
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(str.getBytes().length);
            byteBuffer2.put(str.getBytes(StandardCharsets.UTF_8));
            byteBuffer2.flip();
            fileChannel.write(byteBuffer2);
        }
    }
}
