package jdk17.foreignapi;

import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.ValueLayout;

/**
 * JDK 17 Foreign Function & Memory API（第二次孵化特性）
 *
 * <p>
 * 提供安全高效的堆外内存访问能力，替代传统的 sun.misc.Unsafe。
 * 使用 MemorySegment 分配和管理堆外内存。
 *
 * <p>
 * 编译和运行需添加 --add-modules jdk.incubator.foreign
 */
public class ForeignFunctionMemoryDemo {

    public static void main(String[] args) {
        // 使用 try-with-resources 管理资源生命周期
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            // 分配 16 字节的堆外内存
            MemorySegment segment = MemorySegment.allocateNative(16, scope);

            // 写入数据到堆外内存
            segment.set(ValueLayout.JAVA_INT, 0, 100);
            segment.set(ValueLayout.JAVA_INT, 4, 200);
            segment.set(ValueLayout.JAVA_INT, 8, 300);
            segment.set(ValueLayout.JAVA_INT, 12, 400);

            // 从堆外内存读取数据
            int v0 = segment.get(ValueLayout.JAVA_INT, 0);
            int v1 = segment.get(ValueLayout.JAVA_INT, 4);
            int v2 = segment.get(ValueLayout.JAVA_INT, 8);
            int v3 = segment.get(ValueLayout.JAVA_INT, 12);

            System.out.println("堆外内存读取结果:");
            System.out.println("  偏移 0: " + v0);
            System.out.println("  偏移 4: " + v1);
            System.out.println("  偏移 8: " + v2);
            System.out.println("  偏移 12: " + v3);

            // 输出内存段地址信息
            System.out.println("\n内存段信息:");
            System.out.println("  字节大小: " + segment.byteSize());
        }

        System.out.println("\nForeignFunctionMemoryDemo 执行完毕");
    }
}
