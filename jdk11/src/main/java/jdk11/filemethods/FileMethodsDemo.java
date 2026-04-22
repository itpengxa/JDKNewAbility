package jdk11.filemethods;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * JDK 11 Files 读写字符串方法演示
 */
public class FileMethodsDemo {

    public static void main(String[] args) throws Exception {
        Path tempFile = Path.of(System.getProperty("java.io.tmpdir"), "jdk11_demo.txt");

        // writeString: 直接将字符串写入文件
        String content = "Hello JDK 11\n这是第二行\n这是第三行";
        Files.writeString(tempFile, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("写入文件: " + tempFile);
        System.out.println("写入内容:\n" + content);

        // readString: 直接读取整个文件为字符串
        String readContent = Files.readString(tempFile);
        System.out.println("\n读取内容:");
        System.out.println(readContent);

        // 验证读写一致性
        System.out.println("内容一致: " + content.equals(readContent));

        // 追加写入
        Files.writeString(tempFile, "\n追加的一行", StandardOpenOption.APPEND);
        System.out.println("\n追加后读取:");
        System.out.println(Files.readString(tempFile));

        // 清理
        Files.deleteIfExists(tempFile);
        System.out.println("\n临时文件已删除");
    }
}
