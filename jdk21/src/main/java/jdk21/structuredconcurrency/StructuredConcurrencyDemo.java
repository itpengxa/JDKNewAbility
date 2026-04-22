package jdk21.structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ExecutionException;

/**
 * JDK 21 结构化并发（预览特性，JEP 453）
 * 使用 StructuredTaskScope 将多个并发子任务组织为一个工作单元，
 * 支持 ShutdownOnFailure（任一失败则取消其余）和 ShutdownOnSuccess（任一成功则取消其余）。
 */
public class StructuredConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== JDK 21 结构化并发演示（预览特性）===\n");

        // 1. ShutdownOnFailure：所有子任务必须成功，任一失败则取消其余
        System.out.println("1. StructuredTaskScope.ShutdownOnFailure:");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<String> task1 = scope.fork(() -> fetchUser());
            StructuredTaskScope.Subtask<String> task2 = scope.fork(() -> fetchOrder());

            scope.join();           // 等待所有子任务完成或被取消
            scope.throwIfFailed();  // 如果有任务失败则抛出异常

            String user = task1.get();
            String order = task2.get();
            System.out.println("   用户: " + user + ", 订单: " + order);
        }

        // 2. ShutdownOnSuccess：获取第一个成功的结果，其余取消
        System.out.println("\n2. StructuredTaskScope.ShutdownOnSuccess:");
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> fastService());
            scope.fork(() -> slowService());

            String result = scope.join().result(); // 返回最先成功的结果
            System.out.println("   最先成功的结果: " + result);
        }

        System.out.println("\n=== 演示结束 ===");
    }

    static String fetchUser() {
        System.out.println("   [fetchUser] 执行中...");
        try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "User-123";
    }

    static String fetchOrder() {
        System.out.println("   [fetchOrder] 执行中...");
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Order-456";
    }

    static String fastService() {
        System.out.println("   [fastService] 执行中...");
        try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "FastResult";
    }

    static String slowService() {
        System.out.println("   [slowService] 执行中...");
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "SlowResult";
    }
}
