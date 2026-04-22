package jdk10.optionalenhancement;

import java.util.Optional;

/**
 * JDK 10 Optional.orElseThrow() 无参方法演示
 */
public class OptionalOrElseThrowDemo {

    public static void main(String[] args) {
        Optional<String> present = Optional.of("Java 10");
        Optional<String> empty = Optional.empty();

        // orElseThrow() 无参版本：值存在时返回，否则抛出 NoSuchElementException
        String value = present.orElseThrow();
        System.out.println("有值时 orElseThrow() 返回: " + value);

        // 无参 orElseThrow 语义更清晰，表示"这里必须有值"
        try {
            empty.orElseThrow();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("无值时 orElseThrow() 抛出: " + e.getClass().getSimpleName());
        }

        // 对比 JDK 8 的有参版本（仍然可用）
        String value2 = present.orElseThrow(() -> new IllegalStateException("自定义异常"));
        System.out.println("有参 orElseThrow 返回: " + value2);
    }
}
