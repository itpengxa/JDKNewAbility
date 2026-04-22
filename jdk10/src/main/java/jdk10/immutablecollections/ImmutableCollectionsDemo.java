package jdk10.immutablecollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JDK 10 不可变集合复制方法演示
 */
public class ImmutableCollectionsDemo {

    public static void main(String[] args) {
        // 原始可变集合
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Java");
        mutableList.add("Python");
        mutableList.add("Go");

        // List.copyOf: 从现有集合创建不可变列表
        List<String> immutableList = List.copyOf(mutableList);
        System.out.println("List.copyOf: " + immutableList);

        // Set.copyOf: 从现有集合创建不可变集合
        Set<String> immutableSet = Set.copyOf(mutableList);
        System.out.println("Set.copyOf: " + immutableSet);

        // Map.copyOf: 从现有映射创建不可变映射
        Map<String, Integer> mutableMap = Map.of("Alice", 25, "Bob", 30);
        Map<String, Integer> immutableMap = Map.copyOf(mutableMap);
        System.out.println("Map.copyOf: " + immutableMap);

        // Collectors.toUnmodifiableList: Stream 收集为不可变列表
        List<String> unmodifiableList = mutableList.stream()
                .filter(s -> s.length() > 2)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("Collectors.toUnmodifiableList: " + unmodifiableList);

        // 验证不可变性
        try {
            immutableList.add("Rust");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.copyOf 结果不可修改");
        }

        try {
            unmodifiableList.add("Rust");
        } catch (UnsupportedOperationException e) {
            System.out.println("toUnmodifiableList 结果不可修改");
        }
    }
}
