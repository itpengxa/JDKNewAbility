package jdk18.simplewebserver;

import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.net.InetSocketAddress;
import java.nio.file.Path;

/**
 * JDK 18 Simple Web Server（正式特性）
 *
 * <p>
 * 使用 com.sun.net.httpserver.SimpleFileServer 快速启动一个静态文件服务器，
 * 方便本地测试和原型开发。
 *
 * <p>
 * 本示例在 8000 端口启动服务器，输出访问日志，运行 5 秒后自动关闭。
 */
public class SimpleWebServerDemo {

    public static void main(String[] args) throws Exception {
        // 服务器根目录：当前工作目录
        Path root = Path.of(".").toAbsolutePath().normalize();
        InetSocketAddress address = new InetSocketAddress(8000);

        // 创建并启动简单文件服务器
        var server = SimpleFileServer.createFileServer(address, root, OutputLevel.VERBOSE);

        System.out.println("简单文件服务器已启动");
        System.out.println("  根目录: " + root);
        System.out.println("  监听地址: http://localhost:8000");
        System.out.println("  5 秒后自动关闭...\n");

        server.start();

        // 运行 5 秒后关闭，避免长期占用端口
        Thread.sleep(5000);

        server.stop(0);
        System.out.println("\n简单文件服务器已关闭");
        System.out.println("SimpleWebServerDemo 执行完毕");
    }
}
