package jdk9.optionalenhancement;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * JDK 9 Optional 增强演示
 */
public class OptionalEnhancementDemo {

    public static void main(String[] args) {
        Optional<String> present = Optional.of("Java 9");
        Optional<String> empty = Optional.empty();

        // ifPresentOrElse: 值存在时执行一个操作，不存在时执行另一个操作
        System.out.println("ifPresentOrElse (有值):");
        present.ifPresentOrElse(
                v -> System.out.println("值存在: " + v),
                () -> System.out.println("值不存在")
        );

        System.out.println("ifPresentOrElse (无值):");
        empty.ifPresentOrElse(
                v -> System.out.println("值存在: " + v),
                () -> System.out.println("值不存在")
        );

        // stream: 将 Optional 转为 Stream（有值时含一个元素，无值时为空流）
        System.out.println("stream (有值):");
        present.stream().forEach(System.out::println);

        System.out.println("stream (无值):");
        long count = empty.stream().count();
        System.out.println("空 Optional 转 stream 后的元素数: " + count);

        // or: 如果值不存在，返回另一个 Optional
        System.out.println("or (有值时返回自身):");
        Optional<String> result1 = present.or(() -> Optional.of("默认值"));
        System.out.println(result1.get());

        System.out.println("or (无值时返回备选):");
        Optional<String> result2 = empty.or(() -> Optional.of("默认值"));
        System.out.println(result2.get());
    }
}
