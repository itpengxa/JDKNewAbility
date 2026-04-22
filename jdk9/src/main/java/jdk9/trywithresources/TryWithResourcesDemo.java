package jdk9.trywithresources;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * JDK 9 try-with-resources 增强演示
 * 允许在 try 语句外声明 effectively final 的资源变量，直接在 try 中使用
 */
public class TryWithResourcesDemo {

    public static void main(String[] args) {
        // JDK 9 之前：资源必须在 try 括号内声明并初始化
        // JDK 9 之后：可以在 try 外声明 effectively final 的变量，在 try 中直接使用
        BufferedReader reader = new BufferedReader(new StringReader("Hello JDK 9\nTry-with-resources"));

        try (reader) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // reader 在这里会自动关闭

        // 也可以同时使用多个外部声明的资源
        BufferedReader reader1 = new BufferedReader(new StringReader("第一行"));
        BufferedReader reader2 = new BufferedReader(new StringReader("第二行"));

        try (reader1; reader2) {
            System.out.println(reader1.readLine());
            System.out.println(reader2.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
