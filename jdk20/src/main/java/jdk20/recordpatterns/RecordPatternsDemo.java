package jdk20.recordpatterns;

/**
 * JDK 20 Record 模式（第二次预览特性）
 * 支持在 instanceof 和 switch 中对 record 进行解构，直接提取组件值。
 */
public class RecordPatternsDemo {

    // 定义几个简单的 record
    record Point(int x, int y) {}
    record ColoredPoint(Point point, String color) {}
    record Rectangle(Point topLeft, Point bottomRight) {}

    public static void main(String[] args) {
        System.out.println("=== JDK 20 Record 模式演示 ===\n");

        // 1. instanceof 中的 record 模式（解构）
        System.out.println("1. instanceof Record 模式解构:");
        Object obj = new Point(10, 20);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("   解构 Point: x=" + x + ", y=" + y);
        }

        // 2. 嵌套 record 模式解构
        System.out.println("\n2. 嵌套 Record 模式解构:");
        Object nested = new ColoredPoint(new Point(5, 5), "red");
        if (nested instanceof ColoredPoint(Point(int x, int y), String color)) {
            System.out.println("   解构 ColoredPoint: point=(" + x + "," + y + "), color=" + color);
        }

        // 3. switch 中的 record 模式（JDK 20 预览）
        System.out.println("\n3. switch 中的 Record 模式:");
        Object[] shapes = {
            new Point(1, 2),
            new ColoredPoint(new Point(3, 4), "blue"),
            new Rectangle(new Point(0, 0), new Point(100, 100)),
            "unknown"
        };

        for (Object shape : shapes) {
            String desc = switch (shape) {
                case Point(int x, int y) -> "Point(" + x + ", " + y + ")";
                case ColoredPoint(Point(int x, int y), String color) -> "ColoredPoint(" + x + "," + y + ", " + color + ")";
                case Rectangle(Point tl, Point br) -> "Rectangle(" + tl + " to " + br + ")";
                default -> "其他类型: " + shape;
            };
            System.out.println("   " + desc);
        }

        System.out.println("\n=== 演示结束 ===");
    }
}
