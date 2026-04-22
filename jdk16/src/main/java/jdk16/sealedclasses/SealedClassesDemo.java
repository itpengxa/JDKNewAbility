package jdk16.sealedclasses;

/**
 * JDK 16 Sealed Classes（第二次预览特性）
 * <p>
 * 使用 sealed 关键字限制类的继承层次，只允许指定的子类继承/实现。
 * 子类必须是 final、sealed 或 non-sealed 之一。
 */
public class SealedClassesDemo {

    // 密封接口：只允许 Circle、Rectangle、Square 实现
    sealed interface Shape permits Circle, Rectangle, Square {
        double area();
    }

    // final 子类：不可再被继承
    final static class Circle implements Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    // sealed 子类：继续限制继承者
    sealed static class Rectangle implements Shape permits Square {
        protected final double width;
        protected final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }
    }

    // non-sealed 子类：开放继承
    non-sealed static class Square extends Rectangle {
        Square(double side) {
            super(side, side);
        }
    }

    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape square = new Square(3);

        System.out.println("Circle 面积: " + circle.area());
        System.out.println("Rectangle 面积: " + rectangle.area());
        System.out.println("Square 面积: " + square.area());

        System.out.println("SealedClassesDemo 执行完毕");
    }
}
