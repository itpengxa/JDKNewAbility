package jdk20.virtualthreads;

/**
 * JDK 20 虚拟线程（第二次预览特性）
 * 虚拟线程是轻量级线程，由 JVM 管理，可大幅减少高并发编程的资源开销。
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== JDK 20 虚拟线程演示 ===\n");

        // 1. 使用 Thread.ofVirtual() 创建并启动虚拟线程
        System.out.println("1. Thread.ofVirtual().start():");
        Thread vt1 = Thread.ofVirtual().name("vt-1").start(() -> {
            System.out.println("   虚拟线程 vt-1 运行中，是否为虚拟线程: " + Thread.currentThread().isVirtual());
        });
        vt1.join();

        // 2. 使用 Thread.startVirtualThread() 便捷方法
        System.out.println("\n2. Thread.startVirtualThread():");
        Thread vt2 = Thread.startVirtualThread(() -> {
            System.out.println("   虚拟线程 vt-2 运行中，线程名: " + Thread.currentThread().getName());
        });
        vt2.join();

        // 3. 使用 newVirtualThreadPerTaskExecutor 批量提交任务
        System.out.println("\n3. Executors.newVirtualThreadPerTaskExecutor():");
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 3; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("   任务 " + taskId + " 在虚拟线程中执行: " + Thread.currentThread().getName());
                });
            }
        }

        System.out.println("\n=== 演示结束 ===");
    }
}
