package jdk8.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64 编码/解码 Demo
 */
public class Base64Demo {

    public static void main(String[] args) {
        String original = "Hello Java 8 Base64! 你好";
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);

        System.out.println("原始字符串: " + original);
        System.out.println();

        System.out.println("=== 1. Basic Base64 ===");
        // Basic: 标准 Base64 编码（+、/、=）
        Base64.Encoder basicEncoder = Base64.getEncoder();
        String basicEncoded = basicEncoder.encodeToString(bytes);
        System.out.println("Basic 编码: " + basicEncoded);

        Base64.Decoder basicDecoder = Base64.getDecoder();
        String basicDecoded = new String(basicDecoder.decode(basicEncoded), StandardCharsets.UTF_8);
        System.out.println("Basic 解码: " + basicDecoded);

        System.out.println("\n=== 2. URL Base64 ===");
        // URL: 使用 - 和 _ 代替 + 和 /，不含 =，适合 URL 和文件名
        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        String urlEncoded = urlEncoder.encodeToString(bytes);
        System.out.println("URL 编码: " + urlEncoded);

        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        String urlDecoded = new String(urlDecoder.decode(urlEncoded), StandardCharsets.UTF_8);
        System.out.println("URL 解码: " + urlDecoded);

        System.out.println("\n=== 3. MIME Base64 ===");
        // MIME: 每 76 个字符换行，适合邮件等文本协议
        String longText = "这是一段很长的文本，用于演示 MIME Base64 编码的换行效果。" +
                "Java 8 提供的 Base64 MIME 编码会在每 76 个字符后自动添加换行符，" +
                "这样可以确保编码结果在邮件等文本传输协议中不会出现问题。" +
                "让我们重复一些内容来让文本更长一些。" +
                "这是一段很长的文本，用于演示 MIME Base64 编码的换行效果。" +
                "Java 8 提供的 Base64 MIME 编码会在每 76 个字符后自动添加换行符。";
        byte[] longBytes = longText.getBytes(StandardCharsets.UTF_8);

        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        String mimeEncoded = mimeEncoder.encodeToString(longBytes);
        System.out.println("MIME 编码:\n" + mimeEncoded);

        Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
        String mimeDecoded = new String(mimeDecoder.decode(mimeEncoded), StandardCharsets.UTF_8);
        System.out.println("MIME 解码: " + mimeDecoded);

        System.out.println("\n=== 4. 无填充编码 ===");
        // 使用 withoutPadding() 去除末尾的 = 填充字符
        Base64.Encoder noPaddingEncoder = Base64.getEncoder().withoutPadding();
        String noPadding = noPaddingEncoder.encodeToString(bytes);
        System.out.println("无填充编码: " + noPadding);

        // 解码时不需要特别处理，标准解码器可以处理无填充数据
        String noPaddingDecoded = new String(basicDecoder.decode(noPadding), StandardCharsets.UTF_8);
        System.out.println("无填充解码: " + noPaddingDecoded);
    }
}
