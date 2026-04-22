package jdk19.structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.Future;

/**
 * JDK 19 结构化并发（预览特性）
 *
 * <p>
 * 结构化并发（Structured Concurrency）通过 StructuredTaskScope 将多个并发子任务
 * 组织在同一个代码块中，简化错误处理、取消和传播，使并发代码像顺序代码一样清晰。
 *
 * <p>
 * 编译和运行需添加 --enable-preview
 */
public class StructuredConcurrencyDemo {

    // 模拟远程服务调用：获取用户信息
    static String fetchUser() throws InterruptedException {
        Thread.sleep(100);
        return "User:Alice";
    }

    // 模拟远程服务调用：获取订单信息
    static String fetchOrder() throws InterruptedException {
        Thread.sleep(150);
        return "Order:#12345";
    }

    // 模拟可能失败的任务
    static String fetchInventory() throws InterruptedException {
        Thread.sleep(50);
        throw new RuntimeException("库存服务暂时不可用");
    }

    public static void main(String[] args) throws Exception {
        // 1. 基本用法：并行执行两个任务并合并结果
        System.out.println("示例一: 并行获取用户和订单");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> userFuture = scope.fork(StructuredConcurrencyDemo::fetchUser);
            Future<String> orderFuture = scope.fork(StructuredConcurrencyDemo::fetchOrder);

            scope.join();           // 等待所有子任务完成
            scope.throwIfFailed();  // 任一任务失败则抛出异常

            String user = userFuture.resultNow();
            String order = orderFuture.resultNow();
            System.out.println("  结果: " + user + ", " + order);
        }

        // 2. 错误处理：一个任务失败时自动取消其他任务
        System.out.println("\n示例二: 任一任务失败时取消其余任务 (ShutdownOnFailure)");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> userFuture = scope.fork(StructuredConcurrencyDemo::fetchUser);
            Future<String> invFuture = scope.fork(StructuredConcurrencyDemo::fetchInventory);

            try {
                scope.join();
                scope.throwIfFailed();
                System.out.println("  用户: " + userFuture.resultNow() + ", 库存: " + invFuture.resultNow());
            } catch (Exception e) {
                System.out.println("  捕获异常: " + e.getCause().getMessage());
            }
        }

        // 3. 成功导向：任一任务成功即取消其余任务
        System.out.println("\n示例三: 任一任务成功时取消其余任务 (ShutdownOnSuccess)");
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> {
                Thread.sleep(200);
                return "慢速结果";
            });
            scope.fork(() -> {
                Thread.sleep(50);
                return "快速结果";
            });

            String result = scope.join().result();
            System.out.println("  最先成功的结果: " + result);
        }

        System.out.println("\nStructuredConcurrencyDemo 执行完毕");
    }
}
