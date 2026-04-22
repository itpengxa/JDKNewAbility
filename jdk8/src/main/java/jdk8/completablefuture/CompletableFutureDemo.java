package jdk8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 异步编程 Demo
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. 异步执行 ===");
        // supplyAsync: 有返回值的异步任务
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "supplyAsync 结果";
        });
        System.out.println("supplyAsync 返回值: " + supplyFuture.get());

        // runAsync: 无返回值的异步任务
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync 执行无返回值任务");
        });
        runFuture.get();

        System.out.println("\n=== 2. 串行组合 ===");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        // thenApply: 接收上一步结果，转换后返回新结果
        CompletableFuture<String> thenApplyFuture = future.thenApply(s -> s + " World");
        System.out.println("thenApply: " + thenApplyFuture.get());

        // thenAccept: 接收上一步结果，无返回值（消费结果）
        future.thenAccept(s -> System.out.println("thenAccept 消费: " + s));
        sleep(200); // 等待异步执行完成

        // thenRun: 不依赖上一步结果，无返回值，只是在上一步完成后执行
        future.thenRun(() -> System.out.println("thenRun: 上一步已完成，执行额外操作"));
        sleep(200);

        System.out.println("\n=== 3. 并行组合 ===");
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

        // thenCombine: 两个任务并行执行，都完成后合并结果
        CompletableFuture<Integer> combined = future1.thenCombine(future2, (a, b) -> a + b);
        System.out.println("thenCombine 合并结果: " + combined.get());

        // runAfterBoth: 两个任务都完成后执行 Runnable，不依赖结果
        CompletableFuture<Void> afterBoth = future1.runAfterBoth(future2,
                () -> System.out.println("runAfterBoth: 两个任务都完成了"));
        afterBoth.get();

        // acceptEither: 两个任务任意一个完成后，消费其结果
        CompletableFuture<String> fast = CompletableFuture.supplyAsync(() -> {
            sleep(50);
            return "快任务";
        });
        CompletableFuture<String> slow = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "慢任务";
        });
        fast.acceptEither(slow, result -> System.out.println("acceptEither 先完成的: " + result));
        sleep(300);

        System.out.println("\n=== 4. 异常处理 ===");
        // exceptionally: 捕获异常并返回默认值
        CompletableFuture<String> exceptionFuture = CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("模拟异常");
        }).exceptionally(ex -> {
            System.out.println("exceptionally 捕获: " + ex.getMessage());
            return "默认值";
        });
        System.out.println("exceptionally 结果: " + exceptionFuture.get());

        // handle: 无论是否异常都执行，可处理正常结果和异常
        CompletableFuture<String> handleFuture = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("又出错了");
            return "正常结果";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("handle 捕获异常: " + ex.getMessage());
                return "handle 默认值";
            }
            return result;
        });
        System.out.println("handle 结果: " + handleFuture.get());

        System.out.println("\n=== 5. allOf / anyOf ===");
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "任务1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "任务2";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "任务3";
        });

        // allOf: 所有任务完成后返回（无具体结果，需手动获取每个 future 结果）
        CompletableFuture<Void> allDone = CompletableFuture.allOf(f1, f2, f3);
        allDone.get();
        System.out.println("allOf: 所有任务已完成");
        System.out.println("  f1=" + f1.get() + ", f2=" + f2.get() + ", f3=" + f3.get());

        // anyOf: 任意一个任务完成后返回
        CompletableFuture<Object> anyDone = CompletableFuture.anyOf(f1, f2, f3);
        System.out.println("anyOf: 任意一个完成，结果=" + anyDone.get());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
