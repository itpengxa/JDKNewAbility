package jdk12.compactnumber;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * JDK 12 Compact Number Format（紧凑数字格式）
 * 将大数字格式化为简短易读的形式，如 1,000 -> 1K，1,000,000 -> 1M
 */
public class CompactNumberFormatDemo {

    public static void main(String[] args) {
        System.out.println("=== JDK 12 紧凑数字格式演示 ===\n");

        // 获取紧凑数字格式的实例（短格式）
        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        // 获取紧凑数字格式的实例（长格式）
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.LONG);

        // 获取英文环境的紧凑格式
        NumberFormat usShort = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat usLong = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        long[] numbers = {1000, 1500, 10000, 100000, 1000000, 1500000, 1000000000L};

        System.out.println("中文短格式:");
        for (long num : numbers) {
            System.out.printf("  %,d -> %s%n", num, shortFormat.format(num));
        }

        System.out.println();
        System.out.println("中文长格式:");
        for (long num : numbers) {
            System.out.printf("  %,d -> %s%n", num, longFormat.format(num));
        }

        System.out.println();
        System.out.println("英文短格式:");
        for (long num : numbers) {
            System.out.printf("  %,d -> %s%n", num, usShort.format(num));
        }

        System.out.println();
        System.out.println("英文长格式:");
        for (long num : numbers) {
            System.out.printf("  %,d -> %s%n", num, usLong.format(num));
        }
    }
}
