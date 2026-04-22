package jdk21.recordpatterns;

/**
 * JDK 21 Record 模式（正式特性）
 * 在 instanceof 和 switch 中对 record 进行模式匹配与嵌套解构。
 */
public class RecordPatternsDemo {

    record Point(int x, int y) {}
    record ColoredPoint(Point point, String color) {}
    record Rectangle(Point topLeft, Point bottomRight) {}

    public static void main(String[] args) {
        System.out.println("=== JDK 21 Record 模式演示（正式特性）===\n");

        // 1. instanceof 中的 record 模式
        System.out.println("1. instanceof Record 模式:");
        Object obj = new Point(3, 4);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("   解构 Point: x=" + x + ", y=" + y);
        }

        // 2. 嵌套 record 模式
        System.out.println("\n2. 嵌套 Record 模式:");
        Object nested = new ColoredPoint(new Point(1, 2), "green");
        if (nested instanceof ColoredPoint(Point(int x, int y), String color)) {
            System.out.println("   解构 ColoredPoint: point=(" + x + "," + y + "), color=" + color);
        }

        // 3. switch 中的 record 模式（JDK 21 正式）
        System.out.println("\n3. switch 中的 Record 模式与嵌套:");
        Object[] items = {
            new Point(10, 20),
            new ColoredPoint(new Point(5, 5), "red"),
            new Rectangle(new Point(0, 0), new Point(100, 100)),
            42
        };

        for (Object item : items) {
            String result = switch (item) {
                case Point(int x, int y) -> "Point(" + x + ", " + y + ")";
                case ColoredPoint(Point(int x, int y), String c) -> "ColoredPoint(" + x + "," + y + ", " + c + ")";
                case Rectangle(Point tl, Point br) -> "Rectangle(" + tl + " -> " + br + ")";
                default -> "其他: " + item;
            };
            System.out.println("   " + result);
        }

        System.out.println("\n=== 演示结束 ===");
    }
}
