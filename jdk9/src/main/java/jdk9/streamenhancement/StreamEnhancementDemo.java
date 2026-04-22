package jdk9.streamenhancement;

import java.util.stream.Stream;

/**
 * JDK 9 Stream API 增强演示
 */
public class StreamEnhancementDemo {

    public static void main(String[] args) {
        // takeWhile: 当条件为真时取元素，遇到第一个不满足条件的就停止
        System.out.println("takeWhile (x < 5):");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .takeWhile(x -> x < 5)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // dropWhile: 丢弃条件为真的元素，遇到第一个不满足条件的开始保留
        System.out.println("dropWhile (x < 5):");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .dropWhile(x -> x < 5)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // ofNullable: 避免 null 值产生空指针
        String str = null;
        System.out.println("ofNullable with null:");
        Stream.ofNullable(str).forEach(x -> System.out.print(x + " "));
        System.out.println("(无输出)");

        String str2 = "hello";
        System.out.println("ofNullable with value:");
        Stream.ofNullable(str2).forEach(x -> System.out.print(x + " "));
        System.out.println();

        // iterate 重载: 增加 hasNext 条件判断
        System.out.println("iterate (0..9):");
        Stream.iterate(0, x -> x < 10, x -> x + 1)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}
