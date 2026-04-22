package jdk9.interfaceprivate;

/**
 * JDK 9 接口私有方法演示
 */
public class InterfacePrivateMethodDemo {

    public static void main(String[] args) {
        MyService service = new MyServiceImpl();
        service.doWork();
        service.doWorkWithLog();
    }
}

interface MyService {

    void doWork();

    default void doWorkWithLog() {
        // 调用私有方法复用代码
        log("开始执行 doWorkWithLog");
        doWork();
        log("结束执行 doWorkWithLog");
    }

    // JDK 9 开始接口可以定义私有方法
    private void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class MyServiceImpl implements MyService {
    @Override
    public void doWork() {
        System.out.println("MyServiceImpl 正在工作...");
    }
}
