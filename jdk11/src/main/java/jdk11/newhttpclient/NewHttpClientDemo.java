package jdk11.newhttpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * JDK 11 标准 HttpClient 演示
 * 发送同步/异步 GET、POST 请求
 */
public class NewHttpClientDemo {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // 1. 同步 GET 请求
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();

        System.out.println("=== 同步 GET ===");
        HttpResponse<String> syncResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("状态码: " + syncResponse.statusCode());
        System.out.println("响应体前 300 字符: " + syncResponse.body().substring(0, Math.min(300, syncResponse.body().length())));

        // 2. 异步 GET 请求
        System.out.println("\n=== 异步 GET ===");
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(getRequest, HttpResponse.BodyHandlers.ofString());
        future.thenAccept(response -> {
            System.out.println("异步状态码: " + response.statusCode());
            System.out.println("异步响应体前 300 字符: " + response.body().substring(0, Math.min(300, response.body().length())));
        }).join();

        // 3. 同步 POST 请求
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Java 11\",\"type\":\"HttpClient\"}"))
                .build();

        System.out.println("\n=== 同步 POST ===");
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST 状态码: " + postResponse.statusCode());
        System.out.println("POST 响应体前 300 字符: " + postResponse.body().substring(0, Math.min(300, postResponse.body().length())));
    }
}
