package jdk10.localvartype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JDK 10 var 局部变量类型推断演示
 */
public class LocalVarTypeDemo {

    public static void main(String[] args) {
        // 基本类型推断
        var message = "Hello var";
        System.out.println("var 推断字符串: " + message);

        var number = 42;
        System.out.println("var 推断整数: " + number);

        // 集合类型推断
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");
        System.out.println("var 推断 ArrayList: " + list);

        var map = Map.of("A", 1, "B", 2);
        System.out.println("var 推断 Map: " + map);

        // 循环中使用 var
        System.out.println("增强 for 循环中使用 var:");
        for (var item : list) {
            System.out.println("  " + item);
        }

        System.out.println("传统 for 循环中使用 var:");
        for (var i = 0; i < 3; i++) {
            System.out.println("  index: " + i);
        }

        // 匿名类中使用 var（保留具体类型信息）
        var runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名 Runnable 执行");
            }

            // 匿名类可以添加额外方法
            public void extraMethod() {
                System.out.println("匿名类额外方法");
            }
        };
        runnable.run();
        runnable.extraMethod();

        // try-with-resources 中使用 var
        try (var in = LocalVarTypeDemo.class.getResourceAsStream("/settings.json")) {
            if (in != null) {
                System.out.println("资源流读取成功，可用字节: " + in.available());
            } else {
                System.out.println("资源文件未找到（演示用，不影响 var 特性展示）");
            }
        } catch (Exception e) {
            System.out.println("异常: " + e.getMessage());
        }
    }
}
