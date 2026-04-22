package jdk15.sealedclasses;

/**
 * JDK 15 密封类（Sealed Classes，预览特性）
 * 使用 sealed、permits、non-sealed 控制类的继承层次。
 * 密封类限制哪些类/接口可以实现或继承它。
 */
public class SealedClassesDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 15 密封类演示 ===\n");

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape square = new Square(3);

        System.out.println("图形信息:");
        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(square);

        System.out.println();

        // 展示继承层次
        System.out.println("继承层次检查:");
        System.out.println("Circle 的父类: " + Circle.class.getSuperclass().getSimpleName());
        System.out.println("Rectangle 的父类: " + Rectangle.class.getSuperclass().getSimpleName());
        System.out.println("Square 的父类: " + Square.class.getSuperclass().getSimpleName());
    }

    static void printShapeInfo(Shape shape) {
        System.out.println("  类型: " + shape.getClass().getSimpleName());
        System.out.println("  面积: " + shape.area());
        System.out.println("  描述: " + shape.describe());
        System.out.println();
    }

    /**
     * 密封类：只允许 Circle、Rectangle、Square 继承
     */
    abstract static sealed class Shape permits Circle, Rectangle, Square {
        abstract double area();

        String describe() {
            return "这是一个" + getClass().getSimpleName();
        }
    }

    /**
     * final 子类：不可再被继承
     */
    static final class Circle extends Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }
    }

    /**
     * non-sealed 子类：允许自由继承
     */
    static non-sealed class Rectangle extends Shape {
        protected final double width;
        protected final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    /**
     * 继承自 non-sealed 的 Rectangle，不受密封限制
     */
    static class Square extends Rectangle {
        Square(double side) {
            super(side, side);
        }

        @Override
        double area() {
            return width * width;
        }
    }
}
