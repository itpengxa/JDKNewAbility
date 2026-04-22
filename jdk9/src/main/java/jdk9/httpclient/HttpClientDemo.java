package jdk9.httpclient;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.net.URI;

/**
 * JDK 9 新 HttpClient（incubator 模块）演示
 * 发送 GET 请求
 *
 * 编译和运行需要添加模块参数：
 *   --add-modules jdk.incubator.httpclient
 */
public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        // 创建 HttpClient 实例
        HttpClient client = HttpClient.newHttpClient();

        // 构建 GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();

        System.out.println("发送 GET 请求到 https://httpbin.org/get ...");

        // 同步发送请求
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        System.out.println("状态码: " + response.statusCode());
        System.out.println("响应头: " + response.headers().map());
        System.out.println("响应体前 500 字符:\n" + response.body().substring(0, Math.min(500, response.body().length())));
    }
}
