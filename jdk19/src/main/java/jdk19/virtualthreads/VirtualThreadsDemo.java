package jdk19.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

/**
 * JDK 19 虚拟线程（预览特性）
 *
 * <p>
 * 虚拟线程（Virtual Threads）是轻量级线程，由 JVM 管理调度，
 * 可高效创建数万甚至数百万个并发任务，而不会耗尽操作系统线程资源。
 *
 * <p>
 * 编译和运行需添加 --enable-preview
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) throws Exception {
        int taskCount = 100_000;

        // 1. 使用 Thread.startVirtualThread 快速启动单个虚拟线程
        System.out.println("方式一: Thread.startVirtualThread");
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("  虚拟线程运行中: " + Thread.currentThread());
        });
        vt.join();

        // 2. 使用 Executors.newVirtualThreadPerTaskExecutor 批量创建虚拟线程
        System.out.println("\n方式二: Executors.newVirtualThreadPerTaskExecutor");
        System.out.println("  启动 " + taskCount + " 个虚拟线程任务...");

        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, taskCount).forEach(i -> executor.submit(() -> {
                // 模拟短暂阻塞操作
                Thread.sleep(Duration.ofMillis(1));
                if (i == 0) {
                    System.out.println("  任务 " + i + " 在线程 " + Thread.currentThread() + " 执行");
                }
                return i;
            }));
        }
        Duration duration = Duration.between(start, Instant.now());
        System.out.println("  " + taskCount + " 个虚拟线程任务全部完成，耗时: " + duration.toMillis() + " ms");

        // 3. 使用 ThreadFactory 创建虚拟线程
        System.out.println("\n方式三: Thread.ofVirtual().factory()");
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread t = factory.newThread(() -> System.out.println("  通过工厂创建的虚拟线程: " + Thread.currentThread()));
        t.start();
        t.join();

        System.out.println("\nVirtualThreadsDemo 执行完毕");
    }
}
