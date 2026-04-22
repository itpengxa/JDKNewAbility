package jdk11.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * JDK 11 Collection.toArray(IntFunction) 演示
 */
public class CollectionToArrayDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");

        // JDK 11 之前：toArray(T[]) 需要传入一个数组
        String[] oldWay = list.toArray(new String[0]);
        System.out.println("旧方式 toArray(new String[0]):");
        for (String s : oldWay) {
            System.out.println("  " + s);
        }

        // JDK 11 新增：toArray(IntFunction<T[]>) 更简洁，类型安全
        String[] newWay = list.toArray(String[]::new);
        System.out.println("\n新方式 toArray(String[]::new):");
        for (String s : newWay) {
            System.out.println("  " + s);
        }

        // 同样适用于 Set
        Set<Integer> set = Set.of(10, 20, 30);
        Integer[] intArray = set.toArray(Integer[]::new);
        System.out.println("\nSet.toArray(Integer[]::new):");
        for (Integer i : intArray) {
            System.out.println("  " + i);
        }

        // 底层优势：IntFunction 方式可以避免创建空数组的开销，由集合自己决定数组大小
        System.out.println("\n新 API 更简洁且避免了空数组分配的开销");
    }
}
