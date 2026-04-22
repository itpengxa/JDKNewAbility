package jdk20.scopedvalues;

/**
 * JDK 20 作用域值 ScopedValue（预览特性）
 * ScopedValue 用于在同一线程及子线程/虚拟线程中安全、不可变地传递上下文数据，
 * 相比 ThreadLocal 更轻量、更安全（不可变、无泄漏风险）。
 */
public class ScopedValuesDemo {

    // 声明一个 ScopedValue，用于存储当前用户名称
    private static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        System.out.println("=== JDK 20 ScopedValue 演示 ===\n");

        // 1. 在同一线程中绑定并读取 ScopedValue
        System.out.println("1. 同一线程中使用 ScopedValue.where().run():");
        ScopedValue.where(CURRENT_USER, "Alice").run(() -> {
            System.out.println("   当前用户: " + CURRENT_USER.get());
        });

        // 2. 在虚拟线程中继承 ScopedValue
        System.out.println("\n2. 虚拟线程继承 ScopedValue:");
        ScopedValue.where(CURRENT_USER, "Bob").run(() -> {
            System.out.println("   主线程(虚拟线程载体)用户: " + CURRENT_USER.get());

            Thread vt = Thread.startVirtualThread(() -> {
                // 子虚拟线程自动继承父作用域的值
                System.out.println("   子虚拟线程继承用户: " + CURRENT_USER.get());
            });
            try {
                vt.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 3. 嵌套作用域：内层绑定会覆盖外层
        System.out.println("\n3. 嵌套作用域覆盖:");
        ScopedValue.where(CURRENT_USER, "Outer").run(() -> {
            System.out.println("   外层用户: " + CURRENT_USER.get());
            ScopedValue.where(CURRENT_USER, "Inner").run(() -> {
                System.out.println("   内层用户: " + CURRENT_USER.get());
            });
            System.out.println("   回到外层用户: " + CURRENT_USER.get());
        });

        // 4. 作用域外无法访问（未绑定则抛出异常）
        System.out.println("\n4. 作用域外调用 isBound():");
        System.out.println("   当前是否绑定: " + CURRENT_USER.isBound());

        System.out.println("\n=== 演示结束 ===");
    }
}
