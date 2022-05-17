package sample;
//чтение из файла

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileReader {
    static char[] fileReader (String path_scr) throws IOException {
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(path_scr,"rw");
        try (FileChannel fileChannel = randomAccessFile1.getChannel()){
            ByteBuffer byteBuffer1 = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer1);
            byteBuffer1.flip();
            return new String(byteBuffer1.array(), StandardCharsets.UTF_8).toCharArray();
        }
    }
}
