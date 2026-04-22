package jdk12.teeing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JDK 12 Collectors.teeing 演示
 * 同时对同一个流执行两个收集操作，然后将两个结果合并为一个结果
 */
public class TeeingCollectorDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 12 Collectors.teeing 演示 ===\n");

        List<Integer> numbers = List.of(10, 20, 30, 40, 50);
        System.out.println("数字列表: " + numbers);
        System.out.println();

        // 同时计算平均值和总和
        var avgAndSum = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.averagingInt(Integer::intValue),   // 第一个收集器：平均值
                        Collectors.summingInt(Integer::intValue),     // 第二个收集器：总和
                        (avg, sum) -> "平均值=" + avg + ", 总和=" + sum   // 合并函数
                ));
        System.out.println("同时计算平均值和总和: " + avgAndSum);

        // 同时获取最大值和最小值
        var minAndMax = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Integer::compareTo),         // 第一个收集器：最小值
                        Collectors.maxBy(Integer::compareTo),         // 第二个收集器：最大值
                        (min, max) -> "最小值=" + min.orElse(null) + ", 最大值=" + max.orElse(null)
                ));
        System.out.println("同时获取最小值和最大值: " + minAndMax);

        // 同时获取个数和总和
        var countAndSum = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.counting(),                        // 第一个收集器：计数
                        Collectors.summingInt(Integer::intValue),     // 第二个收集器：总和
                        (count, sum) -> new CountAndSum(count, sum)
                ));
        System.out.println("同时获取个数和总和（对象）: " + countAndSum);
    }

    // 简单的记录类用于存放两个结果（JDK 14 之前没有 record，使用普通类）
    static class CountAndSum {
        private final long count;
        private final int sum;

        CountAndSum(long count, int sum) {
            this.count = count;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "CountAndSum{count=" + count + ", sum=" + sum + "}";
        }
    }
}
