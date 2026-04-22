package jdk12.stringmethods;

import java.util.Optional;

/**
 * JDK 12 String 新方法演示
 * - indent：控制缩进
 * - transform：对字符串进行转换
 * - describeConstable：返回 Optional（与常量描述相关）
 */
public class StringMethodsDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 12 String 新方法演示 ===\n");

        // 1. indent(int n)：每行增加/减少 n 个空格缩进
        String text = "第一行\n第二行\n第三行";
        System.out.println("原始文本:");
        System.out.println(text);
        System.out.println();

        System.out.println("indent(4) 增加4空格缩进:");
        System.out.println(text.indent(4));

        System.out.println("indent(-2) 减少2空格缩进（如果行首有空格）:");
        String indented = "  带缩进的行\n    更多缩进的行";
        System.out.println(indented.indent(-2));

        // 2. transform(Function<? super String, ? extends R> f)：对字符串应用函数转换
        System.out.println("=== transform 方法 ===");
        String original = "hello jdk12";
        String upper = original.transform(String::toUpperCase);
        System.out.println("原始: " + original);
        System.out.println("转大写: " + upper);

        Integer length = original.transform(String::length);
        System.out.println("长度: " + length);

        // 3. describeConstable()：返回 Optional<String>
        System.out.println();
        System.out.println("=== describeConstable 方法 ===");
        Optional<String> constable = original.describeConstable();
        System.out.println("describeConstable: " + constable);
        System.out.println("值: " + constable.orElse("empty"));
    }
}
