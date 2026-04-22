package jdk9.collectionfactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK 9 不可变集合工厂方法演示
 */
public class CollectionFactoryDemo {

    public static void main(String[] args) {
        // List.of 创建不可变列表
        List<String> list = List.of("Java", "Python", "Go");
        System.out.println("List.of: " + list);

        // Set.of 创建不可变集合
        Set<String> set = Set.of("Red", "Green", "Blue");
        System.out.println("Set.of: " + set);

        // Map.of 创建不可变映射（最多10个键值对）
        Map<String, Integer> map = Map.of("Alice", 25, "Bob", 30);
        System.out.println("Map.of: " + map);

        // Map.ofEntries 创建任意大小的不可变映射
        Map<String, Integer> largeMap = Map.ofEntries(
                Map.entry("A", 1),
                Map.entry("B", 2),
                Map.entry("C", 3)
        );
        System.out.println("Map.ofEntries: " + largeMap);

        // 尝试修改会抛出 UnsupportedOperationException
        try {
            list.add("Rust");
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变集合不支持添加元素: " + e.getMessage());
        }
    }
}
