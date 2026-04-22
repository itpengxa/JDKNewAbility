package jdk14.npe;

/**
 * JDK 14 友好的 NullPointerException（Helpful NullPointerExceptions）
 * 在 JVM 参数 -XX:+ShowCodeDetailsInExceptionMessages 开启时，
 * NPE 会显示具体哪个变量为 null。
 *
 * 运行方式（需要开启 JVM 参数）:
 * java -XX:+ShowCodeDetailsInExceptionMessages jdk14.npe.HelpfulNpeDemo
 */
public class HelpfulNpeDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 14 友好的 NullPointerException 演示 ===");
        System.out.println("提示: 使用 JVM 参数 -XX:+ShowCodeDetailsInExceptionMessages 可获得详细 NPE 信息\n");

        Person person = new Person("张三", null);

        // 场景1: 调用 null 对象的方法
        try {
            System.out.println("场景1: 调用 null 引用的方法");
            String city = person.address().city();
            System.out.println("城市: " + city);
        } catch (NullPointerException e) {
            System.out.println("捕获到 NPE: " + e.getMessage());
            System.out.println();
        }

        // 场景2: 链式调用中的 null
        try {
            System.out.println("场景2: 链式调用 person.address().street()");
            String street = person.address().street();
            System.out.println("街道: " + street);
        } catch (NullPointerException e) {
            System.out.println("捕获到 NPE: " + e.getMessage());
            System.out.println();
        }

        // 场景3: 数组元素为 null
        try {
            System.out.println("场景3: 数组元素为 null");
            Person[] people = new Person[2];
            people[0] = person;
            String name = people[1].name();
            System.out.println("姓名: " + name);
        } catch (NullPointerException e) {
            System.out.println("捕获到 NPE: " + e.getMessage());
            System.out.println();
        }

        // 场景4: 方法参数为 null
        try {
            System.out.println("场景4: 将 null 传给需要非空的方法");
            printAddress(null);
        } catch (NullPointerException e) {
            System.out.println("捕获到 NPE: " + e.getMessage());
        }
    }

    static void printAddress(Address address) {
        System.out.println("城市: " + address.city());
    }

    // Record 定义
    record Person(String name, Address address) {
    }

    record Address(String city, String street) {
    }
}
