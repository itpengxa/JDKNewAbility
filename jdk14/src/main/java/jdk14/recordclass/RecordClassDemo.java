package jdk14.recordclass;

/**
 * JDK 14 Record 类（预览特性）
 * 用于声明不可变数据的紧凑语法，自动生成构造器、accessor、equals、hashCode、toString
 */
public class RecordClassDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 14 Record 类演示 ===\n");

        // 1. 创建 Record 实例
        Person person1 = new Person("张三", 30, "北京");
        Person person2 = new Person("李四", 25, "上海");
        Person person3 = new Person("张三", 30, "北京");

        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println();

        // 2. 使用自动生成的 accessor 方法
        System.out.println("person1 的姓名: " + person1.name());
        System.out.println("person1 的年龄: " + person1.age());
        System.out.println("person1 的城市: " + person1.city());
        System.out.println();

        // 3. 自动生成的 equals 和 hashCode
        System.out.println("person1.equals(person2): " + person1.equals(person2));
        System.out.println("person1.equals(person3): " + person1.equals(person3));
        System.out.println("person1.hashCode(): " + person1.hashCode());
        System.out.println("person3.hashCode(): " + person3.hashCode());
        System.out.println();

        // 4. 使用紧凑构造器进行验证的 Record
        System.out.println("=== 带验证的 Record ===");
        try {
            Point p1 = new Point(3, 4);
            System.out.println("Point: " + p1);
            System.out.println("距离原点: " + p1.distanceFromOrigin());

            Point p2 = new Point(-1, 5);
            System.out.println("Point: " + p2);
        } catch (IllegalArgumentException e) {
            System.out.println("创建失败: " + e.getMessage());
        }

        System.out.println();

        // 5. Record 可以实现接口
        System.out.println("=== 实现接口的 Record ===");
        Greeter greeter = new Greeter("世界");
        System.out.println(greeter.greet());
    }

    /**
     * 简单的 Record 定义：自动生成构造器、getter、equals、hashCode、toString
     */
    record Person(String name, int age, String city) {
    }

    /**
     * 带紧凑构造器和自定义方法的 Record
     */
    record Point(int x, int y) {
        // 紧凑构造器：在参数列表后直接写验证逻辑，无需重复参数赋值
        Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("坐标不能为负数: x=" + x + ", y=" + y);
            }
        }

        double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }
    }

    interface Greeting {
        String greet();
    }

    /**
     * Record 可以实现接口
     */
    record Greeter(String target) implements Greeting {
        @Override
        public String greet() {
            return "你好, " + target + "!";
        }
    }
}
