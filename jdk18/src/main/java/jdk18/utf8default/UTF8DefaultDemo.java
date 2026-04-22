package jdk18.utf8default;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * JDK 18 UTF-8 by Default（正式特性）
 *
 * <p>
 * JDK 18 起，标准 Java API 默认使用 UTF-8 编码，不再依赖平台默认编码（如 Windows 上的 GBK）。
 * 这减少了跨平台开发中的编码不一致问题。
 */
public class UTF8DefaultDemo {

    public static void main(String[] args) throws Exception {
        // 1. 显示 JVM 默认字符集
        System.out.println("JVM 默认字符集: " + Charset.defaultCharset().name());

        // 2. 创建一个临时 UTF-8 文件并写入中文
        File tempFile = File.createTempFile("jdk18-utf8-demo-", ".txt");
        tempFile.deleteOnExit();

        String content = "JDK 18 默认使用 UTF-8 编码，中文测试: 你好世界";

        // 使用平台默认编码写入（JDK 18 下默认就是 UTF-8）
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)))) {
            writer.write(content);
        }

        // 3. 使用平台默认编码读取
        StringBuilder readWithDefault = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readWithDefault.append(line);
            }
        }
        System.out.println("使用默认编码读取: " + readWithDefault);

        // 4. 显式使用 UTF-8 读取
        String readWithUtf8 = Files.readString(tempFile.toPath(), StandardCharsets.UTF_8);
        System.out.println("使用 UTF-8 读取:   " + readWithUtf8);

        // 5. 验证两者一致
        boolean consistent = readWithDefault.toString().equals(readWithUtf8);
        System.out.println("默认编码与 UTF-8 读取结果一致: " + consistent);

        // 清理
        tempFile.delete();

        System.out.println("\nUTF8DefaultDemo 执行完毕");
    }
}
