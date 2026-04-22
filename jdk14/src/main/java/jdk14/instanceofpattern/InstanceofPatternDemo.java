package jdk14.instanceofpattern;

/**
 * JDK 14 instanceof 模式匹配（预览特性）
 * 在 instanceof 判断的同时声明并赋值局部变量，避免显式强制转换
 */
public class InstanceofPatternDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 14 instanceof 模式匹配演示 ===\n");

        Object obj1 = "Hello JDK 14";
        Object obj2 = 12345;
        Object obj3 = 3.14;

        // 传统写法：需要显式强制转换
        System.out.println("传统 instanceof 写法:");
        if (obj1 instanceof String) {
            String s = (String) obj1;
            System.out.println("  字符串长度: " + s.length());
        }

        System.out.println();

        // JDK 14 模式匹配：判断后直接获得类型化变量
        System.out.println("模式匹配写法:");
        if (obj1 instanceof String s) {
            // s 已经自动转换为 String 类型，可直接使用
            System.out.println("  字符串内容: " + s);
            System.out.println("  字符串长度: " + s.length());
            System.out.println("  大写形式: " + s.toUpperCase());
        }

        if (obj2 instanceof Integer i) {
            System.out.println("  整数平方: " + (i * i));
        }

        if (obj3 instanceof Double d) {
            System.out.println("  浮点数取整: " + d.intValue());
        }

        System.out.println();

        // 在逻辑表达式中使用模式匹配变量
        System.out.println("在条件表达式中使用模式变量:");
        Object value = "Java";
        if (value instanceof String s && s.length() > 3) {
            System.out.println("  是长度大于3的字符串: " + s);
        }

        // 处理多个类型
        System.out.println();
        System.out.println("处理多种类型:");
        printObjectInfo("测试文本");
        printObjectInfo(42);
        printObjectInfo(2.718);
    }

    static void printObjectInfo(Object obj) {
        if (obj instanceof String s) {
            System.out.println("  字符串: " + s + ", 长度=" + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("  整数: " + i + ", 二进制=" + Integer.toBinaryString(i));
        } else if (obj instanceof Double d) {
            System.out.println("  浮点数: " + d + ", 科学计数=" + String.format("%.2e", d));
        } else {
            System.out.println("  未知类型: " + obj.getClass().getName());
        }
    }
}
