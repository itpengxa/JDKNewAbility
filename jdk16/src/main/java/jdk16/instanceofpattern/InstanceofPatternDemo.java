package jdk16.instanceofpattern;

/**
 * JDK 16 instanceof 模式匹配（正式特性）
 * <p>
 * 在 instanceof 判断类型的同时，直接将对象绑定到局部变量，避免显式强制转换。
 */
public class InstanceofPatternDemo {

    public static void main(String[] args) {
        Object obj = "Hello JDK 16";

        // 传统写法：先判断类型，再手动强转
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println("传统写法: " + s.toUpperCase());
        }

        // JDK 16 模式匹配写法：判断 + 绑定变量一步完成
        if (obj instanceof String s) {
            System.out.println("模式匹配: " + s.toUpperCase());
        }

        // 结合条件表达式使用（模式匹配变量的作用域仅限于逻辑与/或的合理范围）
        Object number = 42;
        if (number instanceof Integer i && i > 10) {
            System.out.println("整数且大于10: " + i);
        }

        System.out.println("InstanceofPatternDemo 执行完毕");
    }
}
