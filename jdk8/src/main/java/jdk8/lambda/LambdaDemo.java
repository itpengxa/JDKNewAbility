package jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Lambda 表达式与函数式接口 Demo
 */
public class LambdaDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Lambda 表达式基础 ===");
        // 无参数 Lambda
        Runnable runnable = () -> System.out.println("Hello Lambda!");
        runnable.run();

        // 单参数 Lambda（可省略括号）
        Consumer<String> printer = msg -> System.out.println("打印: " + msg);
        printer.accept("Lambda 参数示例");

        // 多参数 Lambda
        java.util.Comparator<Integer> comparator = (a, b) -> a - b;
        System.out.println("比较 5 和 3: " + comparator.compare(5, 3));

        System.out.println("\n=== 2. 函数式接口 ===");
        // Predicate: 断言，返回 boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("4 是偶数? " + isEven.test(4));
        System.out.println("5 是偶数? " + isEven.test(5));

        // Consumer: 消费一个参数，无返回值
        Consumer<String> consumer = s -> System.out.println("消费: " + s);
        consumer.accept("Consumer 示例");

        // Function: 输入 T，输出 R
        Function<String, Integer> lengthFunc = s -> s.length();
        System.out.println("'Hello' 长度: " + lengthFunc.apply("Hello"));

        // Supplier: 无参数，返回一个结果
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("随机数: " + randomSupplier.get());

        System.out.println("\n=== 3. 方法引用 ===");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 静态方法引用
        names.forEach(LambdaDemo::printStatic);

        // 实例方法引用
        LambdaDemo demo = new LambdaDemo();
        names.forEach(demo::printInstance);

        // 构造方法引用
        Function<String, StringBuilder> builderFunc = StringBuilder::new;
        StringBuilder sb = builderFunc.apply("构造方法引用");
        System.out.println(sb);

        // 类::实例方法引用（Lambda 的第一个参数作为方法调用者）
        names.forEach(String::toUpperCase); // 仅演示语法，需接收返回值才可见效果
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    private static void printStatic(String s) {
        System.out.println("[静态方法] " + s);
    }

    private void printInstance(String s) {
        System.out.println("[实例方法] " + s);
    }
}
