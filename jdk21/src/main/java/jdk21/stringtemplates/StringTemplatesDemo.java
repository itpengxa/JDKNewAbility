package jdk21.stringtemplates;

/**
 * JDK 21 字符串模板（预览特性，JEP 430）
 * 使用 STR 模板处理器，允许在字符串字面量中直接嵌入表达式 \{expression}，
 * 提升可读性并减少拼接错误。
 */
public class StringTemplatesDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 21 字符串模板演示（预览特性）===\n");

        // 1. 基本用法：嵌入变量
        System.out.println("1. 基本变量嵌入:");
        String name = "World";
        String greeting = STR."Hello, \{name}!";
        System.out.println("   " + greeting);

        // 2. 嵌入表达式
        System.out.println("\n2. 嵌入表达式:");
        int a = 10;
        int b = 20;
        String expr = STR."\{a} + \{b} = \{a + b}";
        System.out.println("   " + expr);

        // 3. 嵌入方法调用
        System.out.println("\n3. 嵌入方法调用:");
        String info = STR."当前时间戳: \{System.currentTimeMillis()}";
        System.out.println("   " + info);

        // 4. 多行模板
        System.out.println("\n4. 多行模板:");
        String user = "Alice";
        int age = 30;
        String json = STR."""
            {
              "name": "\{user}",
              "age": \{age},
              "adult": \{age >= 18}
            }""";
        System.out.println("   " + json.replace("\n", "\n   "));

        // 5. 格式化模板（FMT）
        System.out.println("\n5. 格式化模板（FMT）:");
        double pi = Math.PI;
        String formatted = STR."圆周率保留两位小数: \{String.format("%.2f", pi)}";
        System.out.println("   " + formatted);

        System.out.println("\n=== 演示结束 ===");
    }
}
