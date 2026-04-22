package jdk8.optional;

import java.util.Optional;

/**
 * Optional 容器类 Demo
 */
public class OptionalDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Optional 创建 ===");
        // of: 传入非 null 值，若为 null 则抛 NullPointerException
        Optional<String> optOf = Optional.of("Hello");
        System.out.println("Optional.of: " + optOf);

        // ofNullable: 可传入 null，安全创建
        Optional<String> optNullable = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null): " + optNullable);

        Optional<String> optNullableValue = Optional.ofNullable("World");
        System.out.println("Optional.ofNullable('World'): " + optNullableValue);

        // empty: 创建空的 Optional
        Optional<String> optEmpty = Optional.empty();
        System.out.println("Optional.empty(): " + optEmpty);

        System.out.println("\n=== 2. 常用操作 ===");
        Optional<String> optional = Optional.of("Java 8");

        // ifPresent: 值存在时执行操作
        optional.ifPresent(v -> System.out.println("ifPresent 值存在: " + v));

        Optional<String> emptyOpt = Optional.empty();
        emptyOpt.ifPresent(v -> System.out.println("不会执行"));

        // orElse: 值不存在时返回默认值
        String value1 = emptyOpt.orElse("默认值");
        System.out.println("orElse: " + value1);

        // orElseGet: 值不存在时通过 Supplier 生成默认值（延迟计算）
        String value2 = emptyOpt.orElseGet(() -> "Supplier 默认值");
        System.out.println("orElseGet: " + value2);

        // orElseThrow: 值不存在时抛出异常
        try {
            emptyOpt.orElseThrow(() -> new RuntimeException("值不存在"));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow 捕获异常: " + e.getMessage());
        }

        System.out.println("\n=== 3. 转换操作 ===");
        Optional<String> optStr = Optional.of("123");

        // filter: 过滤值
        Optional<String> filtered = optStr.filter(s -> s.length() > 2);
        System.out.println("filter 长度>2: " + filtered);

        Optional<String> filteredFail = optStr.filter(s -> s.length() > 10);
        System.out.println("filter 长度>10: " + filteredFail);

        // map: 映射转换
        Optional<Integer> mapped = optStr.map(Integer::parseInt);
        System.out.println("map 转 Integer: " + mapped);

        // flatMap: 映射并避免嵌套 Optional
        Optional<Optional<String>> nested = optStr.map(s -> Optional.of(s + "嵌套"));
        System.out.println("map 导致嵌套: " + nested);

        Optional<String> flatMapped = optStr.flatMap(s -> Optional.of(s + "扁平化"));
        System.out.println("flatMap 扁平化: " + flatMapped);

        System.out.println("\n=== 4. 链式使用示例 ===");
        String result = Optional.ofNullable(getName())
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .orElse("UNKNOWN");
        System.out.println("链式结果: " + result);
    }

    private static String getName() {
        return "optional";
    }
}
