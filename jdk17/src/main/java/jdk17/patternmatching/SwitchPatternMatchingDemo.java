package jdk17.patternmatching;

/**
 * JDK 17 Switch 模式匹配（预览特性）
 *
 * <p>
 * 在 switch 中使用类型模式、null case、guard patterns（守卫模式），
 * 让 switch 可以处理任意类型并简化类型判断逻辑。
 *
 * <p>
 * 编译和运行需添加 --enable-preview
 */
public class SwitchPatternMatchingDemo {

    public static void main(String[] args) {
        // 1. 类型模式匹配
        System.out.println("类型模式匹配:");
        System.out.println("  format(42) = " + format(42));
        System.out.println("  format(3.14) = " + format(3.14));
        System.out.println("  format(\"hello\") = " + format("hello"));

        // 2. null case 处理
        System.out.println("\nnull case 处理:");
        System.out.println("  format(null) = " + format(null));

        // 3. guard patterns（守卫模式）
        System.out.println("\n守卫模式:");
        System.out.println("  describe(10) = " + describe(10));
        System.out.println("  describe(100) = " + describe(100));
        System.out.println("  describe(-5) = " + describe(-5));

        System.out.println("\nSwitchPatternMatchingDemo 执行完毕");
    }

    static String format(Object obj) {
        return switch (obj) {
            case null -> "值为 null";
            case Integer i -> "整数: " + i;
            case Double d -> "浮点数: " + d;
            case String s -> "字符串, 长度为 " + s.length();
            default -> "未知类型: " + obj.getClass().getSimpleName();
        };
    }

    static String describe(int n) {
        return switch (n) {
            case 0 -> "零";
            case int i when i > 0 && i < 50 -> "正小数 (1-49): " + i;
            case int i when i >= 50 -> "大正数 (>=50): " + i;
            case int i when i < 0 -> "负数: " + i;
            default -> "其他: " + n;
        };
    }
}
