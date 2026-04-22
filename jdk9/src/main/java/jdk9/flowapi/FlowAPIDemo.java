package jdk9.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * JDK 9 Flow API（响应式流）演示
 * 包含 Publisher / Subscriber / Subscription / Processor
 */
public class FlowAPIDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // 创建订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("[Subscriber] 已订阅");
                // 请求 1 个数据
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("[Subscriber] 收到: " + item);
                // 处理完后再请求下一个
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("[Subscriber] 发生错误: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("[Subscriber] 接收完成");
            }
        };

        // 订阅
        publisher.subscribe(subscriber);

        // 发布数据
        System.out.println("[Publisher] 开始发布数据...");
        for (int i = 1; i <= 5; i++) {
            publisher.submit("消息-" + i);
        }

        // 关闭发布者
        publisher.close();

        // 等待异步处理完成
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("演示结束");
    }
}
