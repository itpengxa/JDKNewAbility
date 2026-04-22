package jdk21.virtualthreads;

import java.util.concurrent.Executors;

/**
 * JDK 21 虚拟线程（正式特性）
 * 虚拟线程成为正式特性，极大简化了高并发编程。
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== JDK 21 虚拟线程演示（正式特性）===\n");

        // 1. 使用 Thread.ofVirtual() 创建并命名虚拟线程
        System.out.println("1. Thread.ofVirtual().start():");
        Thread vt1 = Thread.ofVirtual()
                .name("worker-1")
                .start(() -> System.out.println("   虚拟线程 " + Thread.currentThread().getName() + " 运行中"));
        vt1.join();

        // 2. 使用 Thread.startVirtualThread() 便捷启动
        System.out.println("\n2. Thread.startVirtualThread():");
        Thread vt2 = Thread.startVirtualThread(() ->
            System.out.println("   便捷启动虚拟线程: " + Thread.currentThread().getName())
        );
        vt2.join();

        // 3. 使用 newVirtualThreadPerTaskExecutor 批量并发执行
        System.out.println("\n3. Executors.newVirtualThreadPerTaskExecutor():");
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("   任务 " + taskId + " 在 " + Thread.currentThread().getName() + " 中执行");
                });
            }
        }

        System.out.println("\n=== 演示结束 ===");
    }
}
