package jdk16.recordclass;

/**
 * JDK 16 Record 类（正式特性）
 * <p>
 * 1. 使用 record 简洁地声明不可变数据载体类。
 * 2. 支持本地 record 类（在方法内部定义）。
 * 3. 在 switch 中使用 record（结合模式匹配预览特性，此处做概念性展示）。
 */
public class RecordClassDemo {

    // 传统 POJO 写法：需要大量样板代码
    static class TraditionalPerson {
        private final String name;
        private final int age;

        TraditionalPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return "TraditionalPerson{name='" + name + "', age=" + age + "}";
        }
    }

    // record 写法：自动生成构造器、访问器、equals、hashCode、toString
    record Person(String name, int age) {
    }

    public static void main(String[] args) {
        // 1. 使用 record
        Person person = new Person("Alice", 30);
        System.out.println("record 对象: " + person);
        System.out.println("name 访问器: " + person.name());
        System.out.println("age 访问器: " + person.age());

        // 2. 本地 record 类
        record Point(int x, int y) {
            // 可以在 record 中定义额外方法
            double distanceToOrigin() {
                return Math.sqrt(x * x + y * y);
            }
        }
        Point p = new Point(3, 4);
        System.out.println("本地 record Point: " + p + ", 到原点距离: " + p.distanceToOrigin());

        // 3. record 在 switch 中的使用（JDK 16 时 switch 模式匹配仍为预览特性，
        //    这里用 record 组件访问做示意，展示 record 与模式匹配的天然契合）
        Person target = new Person("Bob", 25);
        String description = switchDescribe(target);
        System.out.println("switch 描述: " + description);

        System.out.println("RecordClassDemo 执行完毕");
    }

    static String switchDescribe(Person person) {
        // JDK 16 正式版中，标准 switch 表达式可以返回结果，
        // 结合后续版本的模式匹配可进一步简化。此处展示基于字段值的 switch。
        return switch (person.age()) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 -> person.name() + " 是未成年人";
            default -> person.name() + " 已成年";
        };
    }
}
