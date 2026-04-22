package jdk9.processhandle;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

/**
 * JDK 9 ProcessHandle API 演示
 * 获取当前进程信息、遍历所有进程
 */
public class ProcessHandleDemo {

    public static void main(String[] args) {
        // 获取当前进程句柄
        ProcessHandle current = ProcessHandle.current();
        System.out.println("当前进程 PID: " + current.pid());

        // 获取进程信息
        ProcessHandle.Info info = current.info();
        System.out.println("命令: " + info.command().orElse("未知"));
        System.out.println("参数: " + info.arguments().map(a -> String.join(", ", a)).orElse("无"));
        System.out.println("启动时间: " + info.startInstant().map(Instant::toString).orElse("未知"));
        System.out.println("CPU 时间: " + info.totalCpuDuration().map(Duration::toMillis).map(ms -> ms + " ms").orElse("未知"));
        System.out.println("用户: " + info.user().orElse("未知"));

        // 遍历所有可见进程
        System.out.println("\n--- 所有进程列表 (前10个) ---");
        ProcessHandle.allProcesses()
                .sorted(Comparator.comparingLong(ProcessHandle::pid))
                .limit(10)
                .forEach(ph -> {
                    String cmd = ph.info().command().map(cmdPath -> {
                        int idx = cmdPath.lastIndexOf("\\");
                        return idx >= 0 ? cmdPath.substring(idx + 1) : cmdPath;
                    }).orElse("[未知]");
                    System.out.println("PID: " + ph.pid() + ", 命令: " + cmd);
                });

        // 检查父进程
        current.parent().ifPresent(parent ->
                System.out.println("\n父进程 PID: " + parent.pid())
        );
    }
}
