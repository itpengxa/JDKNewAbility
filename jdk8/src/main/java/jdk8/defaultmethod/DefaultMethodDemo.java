package jdk8.defaultmethod;

/**
 * 接口默认方法与静态方法 Demo
 */
public class DefaultMethodDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. 接口默认方法 ===");
        Vehicle car = new Car();
        car.print();          // 调用默认方法
        car.showBrand();      // 调用抽象方法实现

        Vehicle bike = new Bike();
        bike.print();         // Bike 重写了默认方法
        bike.showBrand();

        System.out.println("\n=== 2. 接口静态方法 ===");
        // 直接通过接口调用静态方法
        Vehicle.blowHorn();
        Vehicle.checkEngine();

        System.out.println("\n=== 3. 多接口默认方法冲突解决 ===");
        MultiImpl multi = new MultiImpl();
        multi.sayHello();
    }
}

// 定义接口，包含默认方法和静态方法
interface Vehicle {
    // 抽象方法
    void showBrand();

    // 默认方法：接口提供默认实现，实现类可选择重写
    default void print() {
        System.out.println("Vehicle 默认方法: 我是一辆交通工具");
    }

    // 静态方法：属于接口本身，不能被子类继承
    static void blowHorn() {
        System.out.println("Vehicle 静态方法: 按喇叭!!!");
    }

    static void checkEngine() {
        System.out.println("Vehicle 静态方法: 检查引擎状态");
    }
}

// 另一个接口，用于演示默认方法冲突
interface Greeting {
    default void sayHello() {
        System.out.println("Greeting: Hello!");
    }
}

interface Farewell {
    default void sayHello() {
        System.out.println("Farewell: Goodbye!");
    }
}

// 实现类 1：使用默认方法
class Car implements Vehicle {
    @Override
    public void showBrand() {
        System.out.println("Car 品牌: 大众");
    }
    // 不重写 print()，直接使用接口默认实现
}

// 实现类 2：重写默认方法
class Bike implements Vehicle {
    @Override
    public void showBrand() {
        System.out.println("Bike 品牌: 捷安特");
    }

    @Override
    public void print() {
        System.out.println("Bike 重写默认方法: 我是一辆自行车");
    }
}

// 多接口实现，解决默认方法冲突
class MultiImpl implements Greeting, Farewell {
    // 必须重写冲突的默认方法，并可通过 接口名.super.方法名 指定调用哪个
    @Override
    public void sayHello() {
        Greeting.super.sayHello();
        Farewell.super.sayHello();
        System.out.println("MultiImpl: 自定义实现");
    }
}
