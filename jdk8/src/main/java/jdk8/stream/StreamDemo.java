package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API Demo
 */
public class StreamDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Stream 创建 ===");
        // 从集合创建
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> streamFromList = numbers.stream();
        System.out.println("从 List 创建 Stream");

        // 从数组创建
        Stream<String> streamFromArray = Stream.of("a", "b", "c");
        System.out.println("从数组创建 Stream");

        // 使用 Stream.builder
        Stream<String> builtStream = Stream.<String>builder().add("x").add("y").build();
        System.out.println("使用 Stream.builder 创建");

        // 无限流（配合 limit）
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("无限流: " + infiniteStream.collect(Collectors.toList()));

        // 生成流
        Stream<Double> generateStream = Stream.generate(Math::random).limit(3);
        System.out.println("生成流: " + generateStream.collect(Collectors.toList()));

        System.out.println("\n=== 2. 中间操作 ===");
        List<Integer> list = Arrays.asList(5, 2, 8, 2, 9, 1, 5);

        // filter: 过滤
        List<Integer> evens = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("filter 偶数: " + evens);

        // map: 映射转换
        List<Integer> squares = list.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("map 平方: " + squares);

        // sorted: 排序
        List<Integer> sorted = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("sorted 排序: " + sorted);

        // distinct: 去重
        List<Integer> distinct = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct 去重: " + distinct);

        // limit: 限制数量
        List<Integer> limited = list.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("limit 前3个: " + limited);

        System.out.println("\n=== 3. 终止操作 ===");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // collect: 收集为集合
        List<Integer> collected = nums.stream().collect(Collectors.toList());
        System.out.println("collect: " + collected);

        // forEach: 遍历
        System.out.print("forEach: ");
        nums.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // reduce: 归约
        int sum = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("reduce 求和: " + sum);

        // count: 计数
        long count = nums.stream().count();
        System.out.println("count: " + count);

        System.out.println("\n=== 4. 并行流 ===");
        List<Integer> largeList = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());

        // 串行流求和
        long serialSum = largeList.stream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("串行流求和: " + serialSum);

        // 并行流求和
        long parallelSum = largeList.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("并行流求和: " + parallelSum);

        // 也可通过 stream().parallel() 切换为并行流
        long parallelSum2 = largeList.stream()
                .parallel()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("stream().parallel() 求和: " + parallelSum2);
    }
}
