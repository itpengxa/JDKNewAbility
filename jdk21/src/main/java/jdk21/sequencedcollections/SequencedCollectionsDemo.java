package jdk21.sequencedcollections;

import java.util.*;

/**
 * JDK 21 SequencedCollection / SequencedSet / SequencedMap（正式特性）
 * 为有序集合新增统一接口，提供在头部/尾部操作元素的能力，以及 reversed() 逆序视图。
 */
public class SequencedCollectionsDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 21 SequencedCollection 演示 ===\n");

        // 1. SequencedList (ArrayDeque 实现 SequencedCollection)
        System.out.println("1. ArrayDeque 作为 SequencedCollection:");
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("B");
        deque.addFirst("A");
        deque.addLast("C");
        System.out.println("   初始: " + deque);
        System.out.println("   getFirst(): " + deque.getFirst());
        System.out.println("   getLast(): " + deque.getLast());
        System.out.println("   reversed(): " + deque.reversed());
        deque.removeFirst();
        deque.removeLast();
        System.out.println("   removeFirst + removeLast 后: " + deque);

        // 2. LinkedHashSet 实现 SequencedSet
        System.out.println("\n2. LinkedHashSet 作为 SequencedSet:");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        System.out.println("   初始: " + set);
        System.out.println("   reversed(): " + set.reversed());

        // 3. LinkedHashMap 实现 SequencedMap
        System.out.println("\n3. LinkedHashMap 作为 SequencedMap:");
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");
        System.out.println("   初始: " + map);
        System.out.println("   firstEntry(): " + map.firstEntry());
        System.out.println("   lastEntry(): " + map.lastEntry());
        System.out.println("   reversed(): " + map.reversed());
        map.putFirst(0, "Zero");   // JDK 21 新增
        map.putLast(4, "Fourth");  // JDK 21 新增
        System.out.println("   putFirst(0) + putLast(4) 后: " + map);
        map.pollFirstEntry();
        map.pollLastEntry();
        System.out.println("   pollFirstEntry + pollLastEntry 后: " + map);

        // 4. List 也是 SequencedCollection
        System.out.println("\n4. List 作为 SequencedCollection:");
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        System.out.println("   初始: " + list);
        System.out.println("   reversed(): " + list.reversed());

        System.out.println("\n=== 演示结束 ===");
    }
}
