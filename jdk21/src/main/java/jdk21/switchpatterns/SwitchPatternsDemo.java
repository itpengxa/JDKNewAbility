package jdk21.switchpatterns;

/**
 * JDK 21 Switch 模式匹配（正式特性）
 * 支持类型模式、null case、guard patterns（when 子句）、record patterns in switch。
 */
public class SwitchPatternsDemo {

    record Point(int x, int y) {}
    record Rectangle(Point p1, Point p2) {}

    public static void main(String[] args) {
        System.out.println("=== JDK 21 Switch 模式匹配演示（正式特性）===\n");

        Object[] inputs = {
            42,
            3.14,
            "hello",
            null,
            new Point(2, 3),
            new Point(10, 10),
            new Rectangle(new Point(0, 0), new Point(5, 5)),
            100L
        };

        for (Object obj : inputs) {
            System.out.println("   输入: " + obj + " -> " + describe(obj));
        }

        System.out.println("\n=== 演示结束 ===");
    }

    static String describe(Object obj) {
        return switch (obj) {
            // 1. null case（直接处理 null，无需额外 if）
            case null -> "值为 null";

            // 2. 类型模式 + guard pattern（when 子句）
            case Integer i when i > 50 -> "大整数: " + i;
            case Integer i -> "整数: " + i;

            // 3. 多个类型合并（多模式）
            case Double d -> "浮点数: " + d;
            case Long l -> "长整数: " + l;

            // 4. record pattern in switch
            case Point(int x, int y) when x == y -> "对角线上的点: (" + x + "," + y + ")";
            case Point(int x, int y) -> "点: (" + x + "," + y + ")";
            case Rectangle(Point p1, Point p2) -> "矩形: " + p1 + " 到 " + p2;

            // 5. 默认 case
            case String s -> "字符串: " + s;
            default -> "未知类型: " + obj.getClass().getSimpleName();
        };
    }
}
