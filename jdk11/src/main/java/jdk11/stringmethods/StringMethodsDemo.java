package jdk11.stringmethods;

/**
 * JDK 11 字符串新方法演示
 */
public class StringMethodsDemo {

    public static void main(String[] args) {
        // isBlank: 判断字符串是否为空或仅包含空白字符
        System.out.println("\"\".isBlank(): " + "".isBlank());
        System.out.println("\"   \".isBlank(): " + "   ".isBlank());
        System.out.println("\"hello\".isBlank(): " + "hello".isBlank());

        // lines: 按行分割为 Stream
        String multiline = "第一行\n第二行\r\n第三行";
        System.out.println("\nlines() 输出:");
        multiline.lines().forEach(line -> System.out.println("  [" + line + "]"));

        // strip: 去除首尾 Unicode 空白字符（比 trim 更全面）
        String spaced = "    hello world \t  "; //   是 em space
        System.out.println("\n原字符串长度: " + spaced.length());
        System.out.println("strip() 后长度: " + spaced.strip().length());
        System.out.println("strip() 结果: [" + spaced.strip() + "]");

        // stripLeading: 去除首部空白
        System.out.println("stripLeading() 结果: [" + spaced.stripLeading() + "]");

        // stripTrailing: 去除尾部空白
        System.out.println("stripTrailing() 结果: [" + spaced.stripTrailing() + "]");

        // repeat: 重复字符串
        String star = "*";
        System.out.println("\n\"*\".repeat(10): " + star.repeat(10));
        System.out.println("\"Java \".repeat(3): " + "Java ".repeat(3));

        // trim 与 strip 的对比
        String unicodeSpace = " text "; // em space
        System.out.println("\ntrim 与 strip 对比:");
        System.out.println("  trim 后长度: " + unicodeSpace.trim().length());
        System.out.println("  strip 后长度: " + unicodeSpace.strip().length());
    }
}
